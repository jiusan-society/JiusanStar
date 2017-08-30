package gov.jiusan.star.score;

import gov.jiusan.star.score.api.GeneralResponse;
import gov.jiusan.star.score.api.RetrieveResponse;
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
     * @param scoreDTO an incoming request that from the Frontend
     * @return a response info
     */
    public GeneralResponse createScore(gov.jiusan.star.score.api.Score scoreDTO) {
        if (StringUtils.isEmpty(scoreDTO.getConferActivity())) {
            return GeneralResponse.NO_CONFER_ACTIVITY_SCORE;
        }
        if (StringUtils.isEmpty(scoreDTO.getSocialWork())) {
            return GeneralResponse.NO_SOCIAL_WORK_SCORE;
        }
        if (StringUtils.isEmpty(scoreDTO.getSocialContribution())) {
            return GeneralResponse.NO_SOCIAL_CONTRIBUTION_SCORE;
        }
        if (StringUtils.isEmpty(scoreDTO.getPoliticActivity())) {
            return GeneralResponse.NO_POLITIC_ACTIVITY_SCORE;
        }

        Score score = ScoreUtil.transferToEntity(scoreDTO);
        scoreDAO.create(score);
        return GeneralResponse.SUCCESS;
    }

    /**
     * retrieve the score info
     *
     * @param seq the score info's seq
     * @return a response info
     */
    public RetrieveResponse retrieveScore(Long seq) {
        Optional<Score> score = scoreDAO.retrieve(seq);
        return score.map(score1 -> RetrieveResponse.SUCCESS(ScoreUtil.transferToDTO(score1))).orElse(RetrieveResponse.NO_SCORE);
    }

    /**
     * update the score info
     *
     * @param score an incoming request that from the Frontend
     * @param seq            the score info's seq
     * @return a response info
     */
    public UpdateResponse updateScore(gov.jiusan.star.score.api.Score score, Long seq) {
        if (StringUtils.isEmpty(score.getConferActivity())) {
            return UpdateResponse.NO_CONFER_ACTIVITY_SCORE;
        }
        if (StringUtils.isEmpty(score.getSocialWork())) {
            return UpdateResponse.NO_SOCIAL_WORK_SCORE;
        }
        if (StringUtils.isEmpty(score.getSocialContribution())) {
            return UpdateResponse.NO_SOCIAL_CONTRIBUTION_SCORE;
        }
        if (StringUtils.isEmpty(score.getPoliticActivity())) {
            return UpdateResponse.NO_POLITIC_ACTIVITY_SCORE;
        }
        Optional<Score> toBeUpdated = scoreDAO.retrieve(seq);
        if (!toBeUpdated.isPresent()) {
            return UpdateResponse.NO_SCORE;
        }
        ScoreUtil.mergeToEntity(toBeUpdated.get(), score);
        Score updated = scoreDAO.update(toBeUpdated.get());
        return UpdateResponse.SUCCESS(ScoreUtil.transferToDTO(updated));
    }

    /**
     * delete the score info
     *
     * @param seq the score info's seq
     * @return a response info
     */
    public GeneralResponse deleteScore(Long seq) {
        Optional<Score> toBeDeleted = scoreDAO.retrieve(seq);
        if (!toBeDeleted.isPresent()) {
            return GeneralResponse.NO_SCORE;
        }
        scoreDAO.delete(toBeDeleted.get());
        return GeneralResponse.SUCCESS;
    }

}
