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
    public void create(gov.jiusan.star.score.model.Score model) {
        Score score = new Score();
        ScoreUtil.setToEntity(score, model);
        scoreRepository.create(score);
    }

    /**
     * retrieve the score info
     *
     * @param seq
     * @return
     */
    public Optional<Score> find(Long seq) {
        return Optional.ofNullable(scoreRepository.findOne(seq));
    }

    /**
     * update the score info
     *
     * @param seq
     * @param model
     * @return
     */
    public void update(Long seq, gov.jiusan.star.score.model.Score model) {
        Optional<Score> score = find(seq);
        if (score.isPresent()) {
            Score existedScore = score.get();
            ScoreUtil.setToEntity(existedScore, model);
            scoreRepository.update(existedScore);
        }
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
