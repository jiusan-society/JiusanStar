package gov.jiusan.star.sheet_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

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

    public List<SheetPlan> findAll() {
        return repository.findAll();
    }

}
