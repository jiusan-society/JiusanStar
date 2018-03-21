package gov.jiusan.star.score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Marcus Lin
 */
@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    /**
     * create the score info
     *
     * @param model
     */
    public Long create(gov.jiusan.star.score.model.Score model) {
        Score score = new Score();
        ScoreUtil.setToEntity(score, model);
        return scoreRepository.create(score);
    }

    /**
     * retrieve the score info
     *
     * @param seq
     * @return
     */
    public Optional<Score> find(Long seq) {
        if (seq == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(scoreRepository.findOne(seq));
    }

    /**
     * update the score info
     *
     * @param existedScore
     * @param model
     * @return
     */
    public Long update(Score existedScore, gov.jiusan.star.score.model.Score model) {
        ScoreUtil.setToEntity(existedScore, model);
        return scoreRepository.update(existedScore);
    }

    /**
     * find all scores
     *
     * @return
     */
    public List<Score> findAll() {
        List<Score> scoreList = new ArrayList<>();
        scoreRepository.findAll().forEach(scoreList::add);
        return scoreList;
    }

}
