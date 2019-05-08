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
import gov.jiusan.star.sheet.model.SheetDTO;
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

    private final SheetRepository repository;

    private final ScoreService sService;

    private final SheetPlanService sPService;

    @Autowired
    public SheetService(SheetRepository repository, ScoreService sService, SheetPlanService sPService) {
        this.repository = repository;
        this.sService = sService;
        this.sPService = sPService;
    }

    public Long create(SheetDTO model) {
        Sheet entity = SheetUtil.convert(model);
        Calendar now = Calendar.getInstance();
        entity.setCreateTime(now);
        entity.setLastUpdateTime(now);
        return repository.save(entity).getSeq();
    }

    public Optional<Sheet> find(Long seq) {
        return Optional.ofNullable(repository.findOne(seq));
    }

    public Sheet update(Sheet entity, SheetDTO model) {
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.getPhases().clear();
        entity.getPhases().addAll(model.getCategoryDTOS().stream().map(SheetUtil::convertRatingPhase).collect(Collectors.toList()));
        entity.setMaxScore(entity.getPhases().stream().mapToInt(Category::getMaxScore).sum());
        entity.setLastUpdateTime(Calendar.getInstance());
        // 更新既有的评分卷，将使今年已派发过的变作失效
        invalidatePlansInCurrentYear();
        return repository.save(entity);
    }

    public void delete(Sheet entity) {
        repository.delete(entity);
    }

    public List<Sheet> findAll() {
        return repository.findAll();
    }

    void dispatchSheet(Sheet sheet, List<Org> orgs) {
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
        List<Details> detailsList = sheet.getPhases().stream().flatMap(p -> p.getDetails().stream()).collect(Collectors.toList());
        for (Details details : detailsList) {
            initScore.put(details.getSeq(), 0);
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
        repository.save(sheet);
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
