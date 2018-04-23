package gov.jiusan.star.sheet;

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

    @Autowired
    public RatingSheetService(RatingSheetRepository repository) {
        this.repository = repository;
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

    public List<RatingSheet> findAll() {
        return repository.findAll();
    }
}
