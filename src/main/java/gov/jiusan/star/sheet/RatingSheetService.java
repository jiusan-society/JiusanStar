package gov.jiusan.star.sheet;

import gov.jiusan.star.org.Org;
import gov.jiusan.star.score.Score;
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
public class RatingSheetService {

    private final RatingSheetRepository repository;

    private final ScoreRepository sRepository;

    @Autowired
    public RatingSheetService(RatingSheetRepository repository, ScoreRepository sRepository) {
        this.repository = repository;
        this.sRepository = sRepository;
    }

    public Long create(gov.jiusan.star.sheet.model.RatingSheet model) {
        RatingSheet entity = RatingSheetUtil.convert(model);
        return repository.create(entity);
    }

    public Optional<RatingSheet> find(Long seq) {
        if (seq == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(repository.findOne(seq));
    }

    public RatingSheet update(RatingSheet entity, gov.jiusan.star.sheet.model.RatingSheet model) {
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.getRatingPhases().clear();
        entity.getRatingPhases().addAll(model.getRatingPhases().stream().map(RatingSheetUtil::convertRatingPhase).collect(Collectors.toList()));
        return repository.update(entity);
    }

    public void delete(RatingSheet entity) {
        repository.delete(entity);
    }

    public List<RatingSheet> findAll() {
        return repository.findAll();
    }

    public void dispatchSheet(RatingSheet sheet, List<Org> orgs) {
        makeOtherSheetsInvalid();
        sheet.setEffective(true);
        if (!sheet.getScores().isEmpty()) {
            sheet.getScores().clear();
        }
        repository.update(sheet);
        for (Org o : orgs) {
            Score score = new Score();
            score.setOrg(o);
            score.setSheet(sheet);
            score.setEffective(true);
            sRepository.create(score);
        }
    }

    private void makeOtherSheetsInvalid() {
        findAll().stream().filter(RatingSheet::isEffective).forEach(s -> {
            s.setEffective(false);
            repository.update(s);
            s.getScores().forEach(score -> {
                score.setEffective(false);
                sRepository.update(score);
            });
        });
    }
}
