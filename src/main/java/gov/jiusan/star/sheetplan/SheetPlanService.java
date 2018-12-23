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

    List<SheetPlan> findAll() {
        List<SheetPlan> sheetPlans = repository.findAll();
        sheetPlans.sort(Comparator.comparing(SheetPlan::getCreateTime).reversed());
        return sheetPlans;
    }

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

    // TODO 待补充逻辑
    // 每年的 1 月 1 日 0 时 0 分 0 秒，使原本的 sheetPlan 失效
    @Scheduled(cron = "0 0 0 1 JAN ? *")
    private void invalidateCurrent() {

    }

}
