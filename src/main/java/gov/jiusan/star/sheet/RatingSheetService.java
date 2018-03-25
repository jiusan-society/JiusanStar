package gov.jiusan.star.sheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
