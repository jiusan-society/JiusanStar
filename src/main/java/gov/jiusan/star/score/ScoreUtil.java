package gov.jiusan.star.score;

/**
 * @author Marcus Lin
 */
public class ScoreUtil {

    public static gov.jiusan.star.score.api.Score transferToDTO(Score score) {
        gov.jiusan.star.score.api.Score scoreDTO = new gov.jiusan.star.score.api.Score();
        scoreDTO.setConferActivity(score.getConferActivity());
        scoreDTO.setSocialWork(score.getSocialWork());
        scoreDTO.setSocialContribution(score.getSocialContribution());
        scoreDTO.setPoliticActivity(score.getPoliticActivity());
        scoreDTO.setPublicity(score.getPublicity());
        scoreDTO.setSubAssessment(score.getSubAssessment());
        scoreDTO.setTotal(score.getTotal());
        scoreDTO.setCreateTime(score.getCreateTime());
        scoreDTO.setLastUpdateTime(score.getLastUpdateTime());
        return scoreDTO;
    }
}
