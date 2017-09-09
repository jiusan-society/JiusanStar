package gov.jiusan.star.score;

import gov.jiusan.star.score.api.CreateRequest;
import gov.jiusan.star.score.api.UpdateRequest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * @author Marcus Lin
 */
@Stateless
public class ScoreService {

    @Inject
    private ScoreDAO scoreDAO;

    /**
     * create the score info
     *
     * @param request the request info
     */
    public void create(CreateRequest request) {
        Score score = new Score();
        score.setConferActivity(request.getConferActivity());
        score.setSocialWork(request.getSocialWork());
        score.setSocialContribution(request.getSocialContribution());
        score.setPoliticActivity(request.getPoliticActivity());
        score.setPublicity(request.getPublicity());
        score.setSubAssessment(request.getSubAssessment());
        score.setTotal(request.getTotal());
        scoreDAO.create(score);
    }

    /**
     * retrieve the score info
     *
     * @param seq the score entity's seq
     * @return a response info
     */
    public Optional<Score> find(Long seq) {
        return scoreDAO.retrieve(seq);
    }

    /**
     * update the score info
     *
     * @param request the request info
     * @param existedScore the existed score entity
     * @return a response info
     */
    public Score update(UpdateRequest request, Score existedScore) {
        existedScore.setConferActivity(request.getConferActivity());
        existedScore.setSocialWork(request.getSocialWork());
        existedScore.setSocialContribution(request.getSocialContribution());
        existedScore.setPoliticActivity(request.getPoliticActivity());
        existedScore.setPublicity(request.getPublicity());
        existedScore.setSubAssessment(request.getSubAssessment());
        existedScore.setTotal(request.getTotal());
        return scoreDAO.update(existedScore);
    }

    /**
     * delete the score info
     *
     * @param score the score entity
     */
    public void delete(Score score) {
        scoreDAO.delete(score);
    }

    /**
     * fetch a list of scores
     *
     * @param first  the position that we start to fetch
     * @param amount the amount that we want to fetch
     * @return a list of scores
     */
    public List<Score> findByPagination(int first, int amount) {
        return scoreDAO.select(first, amount);
    }
}
