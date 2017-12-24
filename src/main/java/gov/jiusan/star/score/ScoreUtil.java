package gov.jiusan.star.score;

import gov.jiusan.star.score.model.ConferActivity;
import gov.jiusan.star.score.model.PoliticActivity;
import gov.jiusan.star.score.model.SocialContribution;
import gov.jiusan.star.score.model.SocialWork;
import gov.jiusan.star.util.JacksonUtil;

/**
 * @author Marcus Lin
 */
public class ScoreUtil {

    public static gov.jiusan.star.score.model.Score convertToDTO(Score score) {
        gov.jiusan.star.score.model.Score scoreDTO = new gov.jiusan.star.score.model.Score();
        scoreDTO.setConferActivity(JacksonUtil.toObj(score.getConferActivity(), ConferActivity.class).get());
        scoreDTO.setSocialWork(JacksonUtil.toObj(score.getSocialWork(), SocialWork.class).get());
        scoreDTO.setSocialContribution(JacksonUtil.toObj(score.getSocialContribution(), SocialContribution.class).get());
        scoreDTO.setPoliticActivity(JacksonUtil.toObj(score.getPoliticActivity(), PoliticActivity.class).get());
        scoreDTO.setPublicity(score.getPublicity());
        scoreDTO.setSubAssessment(score.getSubAssessment());
        scoreDTO.setTotal(score.getTotal());
        scoreDTO.setCreateTime(score.getCreateTime());
        scoreDTO.setLastUpdateTime(score.getLastUpdateTime());
        return scoreDTO;
    }

    public static void setToEntity(Score scoreEntity, gov.jiusan.star.score.model.Score score) {
        scoreEntity.setConferActivity(JacksonUtil.toString(score.getConferActivity()).get());
        scoreEntity.setSocialWork(JacksonUtil.toString(score.getSocialWork()).get());
        scoreEntity.setSocialContribution(JacksonUtil.toString(score.getSocialContribution()).get());
        scoreEntity.setPoliticActivity(JacksonUtil.toString(score.getPoliticActivity()).get());
        scoreEntity.setPublicity(score.getPublicity());
        scoreEntity.setSubAssessment(score.getSubAssessment());
        scoreEntity.setTotal(score.getTotal());
    }

}
