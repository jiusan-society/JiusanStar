package gov.jiusan.star.score;

import gov.jiusan.star.score.api.CreateResponse;
import gov.jiusan.star.score.api.DeleteResponse;
import gov.jiusan.star.score.api.GeneralRequest;
import gov.jiusan.star.score.api.RetrieveResponse;
import gov.jiusan.star.score.api.UpdateResponse;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Marcus Lin
 */
@Stateless
public class ScoreService {

    @PersistenceContext
    private EntityManager em;

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
        em.persist(score);
        return CreateResponse.SUCCESS;
    }

    public RetrieveResponse retrieveScore(Long seq) {
        Score score = em.find(Score.class, seq);
        if (score == null) {
            return RetrieveResponse.NO_SCORE;
        }
        return RetrieveResponse.SUCCESS(score);
    }

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

        Score toBeUpdated = em.find(Score.class, seq);
        ScoreUtil.mergeToEntity(toBeUpdated, generalRequest);
        em.merge(toBeUpdated);
        return UpdateResponse.SUCCESS(toBeUpdated);
    }

    public DeleteResponse deleteScore(Long seq) {
        Score toBeDeleted = em.find(Score.class, seq);
        if (toBeDeleted == null) {
            return DeleteResponse.NO_SCORE;
        }
        em.remove(toBeDeleted);
        return DeleteResponse.SUCCESS;
    }

}
