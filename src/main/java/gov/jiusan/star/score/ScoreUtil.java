package gov.jiusan.star.score;

/**
 * @author Marcus Lin
 */
public class ScoreUtil {

    public static gov.jiusan.star.score.model.Score convertToDTO(Score score) {
        gov.jiusan.star.score.model.Score scoreDTO = new gov.jiusan.star.score.model.Score();
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
