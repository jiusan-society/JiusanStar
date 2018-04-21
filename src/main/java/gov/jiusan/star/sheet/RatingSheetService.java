package gov.jiusan.star.sheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<RatingSheet> findAll() {
        return repository.findAll();
    }
}
