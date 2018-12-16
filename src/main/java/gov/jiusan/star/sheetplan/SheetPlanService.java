package gov.jiusan.star.sheetplan;

import org.springframework.beans.factory.annotation.Autowired;
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

}
