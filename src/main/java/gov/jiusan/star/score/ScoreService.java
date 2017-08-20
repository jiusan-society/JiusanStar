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
    public ScoreCreateResponse createScore(ScoreRequest scoreRequest) {
        if (StringUtils.isEmpty(scoreRequest.getConferActivity())) {
            return ScoreCreateResponse.NO_CONFER_ACTIVITY_SCORE;
        }
        if (StringUtils.isEmpty(scoreRequest.getSocialWork())) {
            return ScoreCreateResponse.NO_SOCIAL_WORK_SCORE;
        }
        if (StringUtils.isEmpty(scoreRequest.getSocialContribution())) {
            return ScoreCreateResponse.NO_SOCIAL_CONTRIBUTION_SCORE;
        }
        if (StringUtils.isEmpty(scoreRequest.getPoliticActivity())) {
            return ScoreCreateResponse.NO_POLITIC_ACTIVITY_SCORE;
        }

        Score score = ScoreUtil.transferToEntity(scoreRequest);
        em.persist(score);
        return ScoreCreateResponse.SUCCESS;
    }

    public ScoreUpdateResponse updateScore(ScoreRequest scoreRequest, Long seq) {
        if (StringUtils.isEmpty(scoreRequest.getConferActivity())) {
            return ScoreUpdateResponse.NO_CONFER_ACTIVITY_SCORE;
        }
        if (StringUtils.isEmpty(scoreRequest.getSocialWork())) {
            return ScoreUpdateResponse.NO_SOCIAL_WORK_SCORE;
        }
        if (StringUtils.isEmpty(scoreRequest.getSocialContribution())) {
            return ScoreUpdateResponse.NO_SOCIAL_CONTRIBUTION_SCORE;
        }
        if (StringUtils.isEmpty(scoreRequest.getPoliticActivity())) {
            return ScoreUpdateResponse.NO_POLITIC_ACTIVITY_SCORE;
        }

        Score score = em.find(Score.class, seq);
        ScoreUtil.mergeToEntity(score, scoreRequest);
        em.merge(score);
        return ScoreUpdateResponse.SUCCESS(score);
    }
}
