package gov.jiusan.star.score;

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

    // TODO[2017-08-19][Marcus Lin]: Need to change the return type to ScoreUpdateResponse
    public ScoreCreateResponse createScore(ScoreGeneralRequest scoreGeneralRequest) {
        if (StringUtils.isEmpty(scoreGeneralRequest.getConferActivity())) {
            return ScoreCreateResponse.NO_CONFER_ACTIVITY_SCORE;
        }
        if (StringUtils.isEmpty(scoreGeneralRequest.getSocialWork())) {
            return ScoreCreateResponse.NO_SOCIAL_WORK_SCORE;
        }
        if (StringUtils.isEmpty(scoreGeneralRequest.getSocialContribution())) {
            return ScoreCreateResponse.NO_SOCIAL_CONTRIBUTION_SCORE;
        }
        if (StringUtils.isEmpty(scoreGeneralRequest.getPoliticActivity())) {
            return ScoreCreateResponse.NO_POLITIC_ACTIVITY_SCORE;
        }

        Score score = ScoreUtil.transferToEntity(scoreGeneralRequest);
        em.persist(score);
        return ScoreCreateResponse.SUCCESS;
    }

    public ScoreRetrieveResponse retrieveScore(Long seq) {
        Score score = em.find(Score.class, seq);
        if (score == null) {
            return ScoreRetrieveResponse.NO_SCORE;
        }
        return ScoreRetrieveResponse.SUCCESS(score);
    }

    public ScoreUpdateResponse updateScore(ScoreGeneralRequest scoreGeneralRequest, Long seq) {
        if (StringUtils.isEmpty(scoreGeneralRequest.getConferActivity())) {
            return ScoreUpdateResponse.NO_CONFER_ACTIVITY_SCORE;
        }
        if (StringUtils.isEmpty(scoreGeneralRequest.getSocialWork())) {
            return ScoreUpdateResponse.NO_SOCIAL_WORK_SCORE;
        }
        if (StringUtils.isEmpty(scoreGeneralRequest.getSocialContribution())) {
            return ScoreUpdateResponse.NO_SOCIAL_CONTRIBUTION_SCORE;
        }
        if (StringUtils.isEmpty(scoreGeneralRequest.getPoliticActivity())) {
            return ScoreUpdateResponse.NO_POLITIC_ACTIVITY_SCORE;
        }

        Score toBeUpdated = em.find(Score.class, seq);
        ScoreUtil.mergeToEntity(toBeUpdated, scoreGeneralRequest);
        em.merge(toBeUpdated);
        return ScoreUpdateResponse.SUCCESS(toBeUpdated);
    }

    public ScoreDeleteResponse deleteScore(Long seq) {
        Score toBeDeleted = em.find(Score.class, seq);
        if (toBeDeleted == null) {
            return ScoreDeleteResponse.NO_SCORE;
        }
        em.remove(toBeDeleted);
        return ScoreDeleteResponse.SUCCESS;
    }

}
