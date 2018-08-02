package gov.jiusan.star.sheet;

import gov.jiusan.star.score.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Marcus Lin
 */
@Service
public class SheetService {

    private final SheetRepository repository;

    private final ScoreRepository sRepository;

    @Autowired
    public SheetService(SheetRepository repository, ScoreRepository sRepository) {
        this.repository = repository;
        this.sRepository = sRepository;
    }

    public Long create(gov.jiusan.star.sheet.model.Sheet model) {
        Sheet entity = SheetUtil.convert(model);
        return repository.create(entity);
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
        return repository.update(entity);
    }

    public void delete(Sheet entity) {
        repository.delete(entity);
    }

    public List<Sheet> findAll() {
        return repository.findAll();
    }

//    public void dispatchSheet(Sheet sheet, List<Org> orgs) {
//        makeOtherSheetsInvalid();
//        sheet.setEffective(true);
//        if (!sheet.getScores().isEmpty()) {
//            sheet.getScores().clear();
//        }
//        repository.update(sheet);
//        for (Org o : orgs) {
//            Score score = new Score();
//            score.setOrg(o);
//            score.setSheet(sheet);
//            score.setEffective(true);
//            sRepository.create(score);
//        }
//    }
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
