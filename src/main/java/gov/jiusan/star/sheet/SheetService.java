package gov.jiusan.star.sheet;

import gov.jiusan.star.org.Org;
import gov.jiusan.star.score.Score;
import gov.jiusan.star.score.ScoreService;
import gov.jiusan.star.sheet.model.SheetDTO;
import gov.jiusan.star.sheetplan.SheetPlan;
import gov.jiusan.star.sheetplan.SheetPlanService;
import gov.jiusan.star.sheetplan.SheetPlanStatus;
import gov.jiusan.star.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
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
        var entity = SheetUtil.convert(model);
        var now = Calendar.getInstance();
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

    public Sheet update(Sheet entity, SheetDTO model) {
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.getPhases().clear();
        entity.getPhases().addAll(model.getPhaseDTOs().stream().map(SheetUtil::convertRatingPhase).collect(Collectors.toList()));
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
        // 同一年只能有一张 plan 生效
        invalidateOtherPlansInCurrentYear();
        var sheetPlan = new SheetPlan();
        sheetPlan.setSheet(sheet);
        sheetPlan.setEffective(true);
        sheetPlan.setStatus(SheetPlanStatus.NORMAL);
        sheetPlan.setEffectiveTime(Calendar.getInstance());
        var expirationTime = Calendar.getInstance();
        expirationTime.clear();
        expirationTime.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR) + 1);
        sheetPlan.setExpirationTime(expirationTime);
        var savedSP = sPService.create(sheetPlan);
        var initScore = new TreeMap<Long, Integer>();
        var detailsList = sheet.getPhases().stream().flatMap(p -> p.getDetails().stream()).collect(Collectors.toList());
        for (var details : detailsList) {
            initScore.put(details.getSeq(), 0);
        }
        for (var o : orgs) {
            var score = new Score();
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
     * 如果同一年有其他生效的 plans，均需使它们失效
     */
    private void invalidateOtherPlansInCurrentYear() {
        sPService.findByCurrentYear().stream()
            .filter(SheetPlan::isEffective)
            .forEach(p -> {
                p.setEffective(false);
                p.setStatus(SheetPlanStatus.INVALID);
            });
    }
}
