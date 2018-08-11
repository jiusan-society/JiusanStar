package gov.jiusan.star.sheet;

import gov.jiusan.star.org.Org;
import gov.jiusan.star.score.Score;
import gov.jiusan.star.score.ScoreService;
import gov.jiusan.star.sheet_plan.SheetPlan;
import gov.jiusan.star.sheet_plan.SheetPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
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

    public Long create(gov.jiusan.star.sheet.model.Sheet model) {
        Sheet entity = SheetUtil.convert(model);
        Calendar now = Calendar.getInstance();
        entity.setCreateTime(now);
        entity.setLastUpdateTime(now);
        return repository.save(entity).getSeq();
    }

    public Optional<Sheet> find(Long seq) {
        if (seq == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(repository.findOne(seq));
    }

    public Sheet update(Sheet entity, gov.jiusan.star.sheet.model.Sheet model) {
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.getPhases().clear();
        entity.getPhases().addAll(model.getPhases().stream().map(SheetUtil::convertRatingPhase).collect(Collectors.toList()));
        entity.setLastUpdateTime(Calendar.getInstance());
        return repository.save(entity);
    }

    public void delete(Sheet entity) {
        repository.delete(entity);
    }

    public List<Sheet> findAll() {
        return repository.findAll();
    }

    public void dispatchSheet(Sheet sheet, List<Org> orgs) {
        SheetPlan sheetPlan = new SheetPlan();
        sheetPlan.setSheet(sheet);
        sheetPlan.setEffective(true);
        sheetPlan.setEffectiveTime(Calendar.getInstance());
        Calendar expirationTime = Calendar.getInstance();
        expirationTime.clear();
        expirationTime.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR) + 1);
        sheetPlan.setExpirationTime(expirationTime);
        SheetPlan savedSP = sPService.create(sheetPlan);
        for (Org o : orgs) {
            Score score = new Score();
            score.setOrg(o);
            score.setSheetPlan(savedSP);
            score.setEffective(true);
            sService.create(score);
        }
        sheet.getSheetPlans().add(savedSP);
        repository.save(sheet);
    }

//
//    private void makeOtherSheetsInvalid() {
//        findAll().stream().filter(Sheet::isEffective).forEach(s -> {
//            s.setEffective(false);
//            repository.update(s);
//            s.getScores().forEach(score -> {
//                score.setEffective(false);
//                sRepository.update(score);
//            });
//        });
//    }
}
