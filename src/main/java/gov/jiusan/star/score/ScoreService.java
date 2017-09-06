package gov.jiusan.star.score;

import gov.jiusan.star.score.api.CreateRequest;
import gov.jiusan.star.score.api.CreateResponse;
import gov.jiusan.star.score.api.DeleteRequest;
import gov.jiusan.star.score.api.DeleteResponse;
import gov.jiusan.star.score.api.RetrieveRequest;
import gov.jiusan.star.score.api.RetrieveResponse;
import gov.jiusan.star.score.api.UpdateRequest;
import gov.jiusan.star.score.api.UpdateResponse;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
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
     * @param request
     * @return a response info
     */
    public CreateResponse createScore(CreateRequest request) {
        if (StringUtils.isEmpty(request.getConferActivity())) {
            return CreateResponse.NO_CONFER_ACTIVITY_SCORE;
        }
        if (StringUtils.isEmpty(request.getSocialWork())) {
            return CreateResponse.NO_SOCIAL_WORK_SCORE;
        }
        if (StringUtils.isEmpty(request.getSocialContribution())) {
            return CreateResponse.NO_SOCIAL_CONTRIBUTION_SCORE;
        }
        if (StringUtils.isEmpty(request.getPoliticActivity())) {
            return CreateResponse.NO_POLITIC_ACTIVITY_SCORE;
        }

        Score score = new Score();
        score.setConferActivity(request.getConferActivity());
        score.setSocialWork(request.getSocialWork());
        score.setSocialContribution(request.getSocialContribution());
        score.setPoliticActivity(request.getPoliticActivity());
        score.setPublicity(request.getPublicity());
        score.setSubAssessment(request.getSubAssessment());
        score.setTotal(request.getTotal());

        scoreDAO.create(score);
        return CreateResponse.SUCCESS;
    }

    /**
     * retrieve the score info
     *
     * @param request
     * @return a response info
     */
    public RetrieveResponse retrieveScore(RetrieveRequest request) {
        Optional<Score> score = scoreDAO.retrieve(request.getSeq());
        return score.map(score1 -> RetrieveResponse.SUCCESS(ScoreUtil.transferToDTO(score1))).orElse(RetrieveResponse.NO_SCORE);
    }

    /**
     * update the score info
     *
     * @param request
     * @return a response info
     */
    public UpdateResponse updateScore(UpdateRequest request) {
        if (StringUtils.isEmpty(request.getConferActivity())) {
            return UpdateResponse.NO_CONFER_ACTIVITY_SCORE;
        }
        if (StringUtils.isEmpty(request.getSocialWork())) {
            return UpdateResponse.NO_SOCIAL_WORK_SCORE;
        }
        if (StringUtils.isEmpty(request.getSocialContribution())) {
            return UpdateResponse.NO_SOCIAL_CONTRIBUTION_SCORE;
        }
        if (StringUtils.isEmpty(request.getPoliticActivity())) {
            return UpdateResponse.NO_POLITIC_ACTIVITY_SCORE;
        }

        Optional<Score> toBeUpdated = scoreDAO.retrieve(request.getSeq());
        if (!toBeUpdated.isPresent()) {
            return UpdateResponse.NO_SCORE;
        }

        Score score = toBeUpdated.get();
        score.setConferActivity(request.getConferActivity());
        score.setSocialWork(request.getSocialWork());
        score.setSocialContribution(request.getSocialContribution());
        score.setPoliticActivity(request.getPoliticActivity());
        score.setPublicity(request.getPublicity());
        score.setSubAssessment(request.getSubAssessment());
        score.setTotal(request.getTotal());

        Score updated = scoreDAO.update(score);
        return UpdateResponse.SUCCESS(ScoreUtil.transferToDTO(updated));
    }

    /**
     * delete the score info
     *
     * @param request
     * @return a response info
     */
    public DeleteResponse deleteScore(DeleteRequest request) {
        Optional<Score> toBeDeleted = scoreDAO.retrieve(request.getSeq());
        if (!toBeDeleted.isPresent()) {
            return DeleteResponse.NO_SCORE;
        }
        scoreDAO.delete(toBeDeleted.get());
        return DeleteResponse.SUCCESS;
    }

}
