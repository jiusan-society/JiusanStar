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

package gov.jiusan.star.sheetplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Marcus Lin
 */
@Service
public class SheetPlanService {

    private final SheetPlanRepository repository;

    @Autowired
    public SheetPlanService(SheetPlanRepository repository) {
        this.repository = repository;
    }

    public SheetPlan create(SheetPlan sheetPlan) {
        Calendar now = Calendar.getInstance();
        sheetPlan.setCreateTime(now);
        sheetPlan.setLastUpdateTime(now);
        return repository.save(sheetPlan);
    }

    // 每年只会有一个生效的 plan
    public List<SheetPlan> findEffectives() {
        return repository.findSheetPlanByEffectiveIsTrue();
    }

    public List<SheetPlan> findByCurrentYear() {
        Calendar now = Calendar.getInstance();
        return repository.findAll().stream()
            .filter(p -> p.getCreateTime().get(Calendar.YEAR) == now.get(Calendar.YEAR))
            .sorted(Comparator.comparing(SheetPlan::getCreateTime).reversed())
            .collect(Collectors.toList());
    }

    // TODO 没完成的那些 score 应该标记为 "不合格"
    // 每年的 1 月 1 日 0 时 0 分 0 秒执行，使当前的 plan 变为 "已逾期" 状态
    @Scheduled(cron = "0 0 0 1 JAN ?")
    private void invalidateCurrent() {
        findEffectives().forEach(p -> p.setStatus(SheetPlanStatus.EXPIRED));
    }

}
