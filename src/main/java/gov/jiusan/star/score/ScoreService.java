package gov.jiusan.star.score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * @author Marcus Lin
 */
@Service
public class ScoreService {

    private final ScoreRepository repository;

    @Autowired
    public ScoreService(ScoreRepository repository) {
        this.repository = repository;
    }

    public Score create(Score score) {
        Calendar now = Calendar.getInstance();
        score.setCreateTime(now);
        score.setLastUpdateTime(now);
        return repository.save(score);
    }
}
