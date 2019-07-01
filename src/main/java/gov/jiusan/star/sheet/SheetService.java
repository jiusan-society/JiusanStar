/*
 * Copyright 2019 Marcus Lin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gov.jiusan.star.sheet;

import gov.jiusan.star.org.Org;
import gov.jiusan.star.score.Score;
import gov.jiusan.star.score.ScoreService;
import gov.jiusan.star.sheet.model.Sheet;
import gov.jiusan.star.sheetplan.SheetPlan;
import gov.jiusan.star.sheetplan.SheetPlanService;
import gov.jiusan.star.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author Marcus Lin
 */
@Service
public class SheetService {

    private final SheetRepository sRepository;

    private final CategoryRepository cRepository;

    private final ScoreService sService;

    private final SheetPlanService sPService;

    @Autowired
    public SheetService(SheetRepository sRepository, ScoreService sService, SheetPlanService sPService, CategoryRepository cRepository) {
        this.sRepository = sRepository;
        this.sService = sService;
        this.sPService = sPService;
        this.cRepository = cRepository;
    }

    public gov.jiusan.star.sheet.Sheet create(Sheet model) {
        gov.jiusan.star.sheet.Sheet sheet = new gov.jiusan.star.sheet.Sheet();
        sheet.setName(model.getName());
        sheet.setDescription(model.getDescription());
        int maxScore = 0;
        for (Sheet.Category c : model.getCategories()) {
            Optional<Category> category_o = cRepository.findByName(c.getName());
            if (!category_o.isPresent()) {
                continue;
            }
            maxScore += category_o.get().getMaxScore();
            sheet.getCategories().add(category_o.get());
            List<Item> items = c.getItems().stream().map(i -> {
                Item item = new Item();
                item.setSheet(sheet);
                item.setCategory(category_o.get());
                item.setDescription(i.getDescription());
                item.setEachScore(i.getEachScore());
                item.setMaxScore(i.getMaxScore());
                item.setCreateTime(Calendar.getInstance());
                item.setLastUpdateTime(Calendar.getInstance());
                return item;
            }).collect(Collectors.toList());
            sheet.getItems().addAll(items);
        }
        sheet.setMaxScore(maxScore);
        Calendar now = Calendar.getInstance();
        sheet.setCreateTime(now);
        sheet.setLastUpdateTime(now);
        return sRepository.save(sheet);
    }



    public Optional<gov.jiusan.star.sheet.Sheet> find(Long seq) {
        return Optional.ofNullable(sRepository.findOne(seq));
    }

    public gov.jiusan.star.sheet.Sheet update(gov.jiusan.star.sheet.Sheet sheet, Sheet model) {
        sheet.setName(model.getName());
        sheet.setDescription(model.getDescription());
        sheet.getCategories().clear();
        sheet.getItems().clear();
        int maxScore = 0;
        for (Sheet.Category c : model.getCategories()) {
            Optional<Category> category_o = cRepository.findByName(c.getName());
            if (!category_o.isPresent()) {
                continue;
            }
            maxScore += category_o.get().getMaxScore();
            sheet.getCategories().add(category_o.get());
            List<Item> items = c.getItems().stream().map(i -> {
                Item item = new Item();
                item.setSheet(sheet);
                item.setCategory(category_o.get());
                item.setDescription(i.getDescription());
                item.setEachScore(i.getEachScore());
                item.setMaxScore(i.getMaxScore());
                item.setCreateTime(Calendar.getInstance());
                item.setLastUpdateTime(Calendar.getInstance());
                return item;
            }).collect(Collectors.toList());
            sheet.getItems().addAll(items);
        }
        sheet.setMaxScore(maxScore);
        sheet.setLastUpdateTime(Calendar.getInstance());
        // 更新既有的评分卷，将使今年已派发过的变作失效
        invalidatePlansInCurrentYear();
        return sRepository.save(sheet);
    }

    public void delete(gov.jiusan.star.sheet.Sheet entity) {
        sRepository.delete(entity);
    }

    public List<gov.jiusan.star.sheet.Sheet> findAll() {
        return sRepository.findAll();
    }

    void dispatchSheet(gov.jiusan.star.sheet.Sheet sheet, List<Org> orgs) {
        // 同一年只能有一张 plan 生效
        invalidatePlansInCurrentYear();
        SheetPlan sheetPlan = new SheetPlan();
        sheetPlan.setSheet(sheet);
        sheetPlan.setEffective(true);
        sheetPlan.setStatus(SheetPlan.Status.NORMAL);
        sheetPlan.setEffectiveTime(Calendar.getInstance());
        Calendar expirationTime = Calendar.getInstance();
        expirationTime.clear();
        expirationTime.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR) + 1);
        sheetPlan.setExpirationTime(expirationTime);
        SheetPlan savedSP = sPService.create(sheetPlan);
        Map<Long, Integer> initScore = new TreeMap<>();
        for (Item i : sheet.getItems()) {
            initScore.put(i.getSeq(), 0);
        }
        for (Org o : orgs) {
            Score score = new Score();
            score.setOrg(o);
            score.setSheetPlan(savedSP);
            score.setsADetails(JacksonUtil.toString(initScore).get());
            score.setaADetails(JacksonUtil.toString(initScore).get());
            sService.create(score);
        }
        sheet.getSheetPlans().add(savedSP);
        sRepository.save(sheet);
    }

    /**
     * 使同一年中生效的 plans 失效
     * 1. 当重新派发评分卷时
     * 2. 当既有的评分卷被更新时
     */
    private void invalidatePlansInCurrentYear() {
        sPService.findByCurrentYear().stream()
            .filter(SheetPlan::isEffective)
            .forEach(p -> {
                p.setEffective(false);
                p.setStatus(SheetPlan.Status.INVALID);
            });
    }
}
