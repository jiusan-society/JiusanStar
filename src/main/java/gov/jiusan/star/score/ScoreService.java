package gov.jiusan.star.score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Marcus Lin
 */
@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    /**
     * create the score info
     *
     * @param model
     */
    public Score create(gov.jiusan.star.score.model.Score model) {
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
     */
    public Score update(Score existedScore, gov.jiusan.star.score.model.Score model) {
        ScoreUtil.setToEntity(existedScore, model);
        return scoreRepository.update(existedScore);
    }

    /**
     * delete the score info
     *
     * @param seq
     */
    public void delete(Long seq) {
        scoreRepository.delete(seq);
    }

}
