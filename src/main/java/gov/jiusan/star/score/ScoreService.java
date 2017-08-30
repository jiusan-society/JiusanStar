package gov.jiusan.star.score;

import gov.jiusan.star.score.api.CreateResponse;
import gov.jiusan.star.score.api.DeleteResponse;
import gov.jiusan.star.score.api.GeneralRequest;
import gov.jiusan.star.score.api.RetrieveResponse;
import gov.jiusan.star.score.api.UpdateResponse;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
     * @param generalRequest an incoming request that from the Frontend
     * @return a response info
     */
    public CreateResponse createScore(GeneralRequest generalRequest) {
        if (StringUtils.isEmpty(generalRequest.getConferActivity())) {
            return CreateResponse.NO_CONFER_ACTIVITY_SCORE;
        }
        if (StringUtils.isEmpty(generalRequest.getSocialWork())) {
            return CreateResponse.NO_SOCIAL_WORK_SCORE;
        }
        if (StringUtils.isEmpty(generalRequest.getSocialContribution())) {
            return CreateResponse.NO_SOCIAL_CONTRIBUTION_SCORE;
        }
        if (StringUtils.isEmpty(generalRequest.getPoliticActivity())) {
            return CreateResponse.NO_POLITIC_ACTIVITY_SCORE;
        }

        Score score = ScoreUtil.transferToEntity(generalRequest);
        scoreDAO.create(score);
        return CreateResponse.SUCCESS;
    }

    /**
     * retrieve the score info
     *
     * @param seq the score info's seq
     * @return a response info
     */
    public RetrieveResponse retrieveScore(Long seq) {
        Optional<Score> score = scoreDAO.retrieve(seq);
        return score.map(RetrieveResponse::SUCCESS).orElse(RetrieveResponse.NO_SCORE);
    }

    /**
     * update the score info
     *
     * @param generalRequest an incoming request that from the Frontend
     * @param seq the score info's seq
     * @return a response info
     */
    public UpdateResponse updateScore(GeneralRequest generalRequest, Long seq) {
        if (StringUtils.isEmpty(generalRequest.getConferActivity())) {
            return UpdateResponse.NO_CONFER_ACTIVITY_SCORE;
        }
        if (StringUtils.isEmpty(generalRequest.getSocialWork())) {
            return UpdateResponse.NO_SOCIAL_WORK_SCORE;
        }
        if (StringUtils.isEmpty(generalRequest.getSocialContribution())) {
            return UpdateResponse.NO_SOCIAL_CONTRIBUTION_SCORE;
        }
        if (StringUtils.isEmpty(generalRequest.getPoliticActivity())) {
            return UpdateResponse.NO_POLITIC_ACTIVITY_SCORE;
        }
        Optional<Score> toBeUpdated = scoreDAO.retrieve(seq);
        if (!toBeUpdated.isPresent()) {
            return UpdateResponse.NO_SCORE;
        }
        ScoreUtil.mergeToEntity(toBeUpdated.get(), generalRequest);
        Score updated = scoreDAO.update(toBeUpdated.get());
        return UpdateResponse.SUCCESS(updated);
    }

    /**
     * delete the score info
     *
     * @param seq the score info's seq
     * @return a response info
     */
    public DeleteResponse deleteScore(Long seq) {
        Optional<Score> toBeDeleted = scoreDAO.retrieve(seq);
        if (!toBeDeleted.isPresent()) {
            return DeleteResponse.NO_SCORE;
        }
        scoreDAO.delete(toBeDeleted.get());
        return DeleteResponse.SUCCESS;
    }

}
