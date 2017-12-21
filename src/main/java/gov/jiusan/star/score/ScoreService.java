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
        score.setConferActivity(model.getConferActivity());
        score.setSocialWork(model.getSocialWork());
        score.setSocialContribution(model.getSocialContribution());
        score.setPoliticActivity(model.getPoliticActivity());
        score.setPublicity(model.getPublicity());
        score.setSubAssessment(model.getSubAssessment());
        score.setTotal(model.getTotal());
        scoreRepository.save(score);
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
            existedScore.setConferActivity(model.getConferActivity());
            existedScore.setSocialWork(model.getSocialWork());
            existedScore.setSocialContribution(model.getSocialContribution());
            existedScore.setPoliticActivity(model.getPoliticActivity());
            existedScore.setPublicity(model.getPublicity());
            existedScore.setSubAssessment(model.getSubAssessment());
            existedScore.setTotal(model.getTotal());
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
