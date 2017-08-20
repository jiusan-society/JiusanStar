package gov.jiusan.star.score;

/**
 * @author Marcus Lin
 */
public class ScoreUtil {

    public static Score transferToEntity(ScoreGeneralRequest scoreGeneralRequest) {
        Score score = new Score();
        score.setConferActivity(scoreGeneralRequest.getConferActivity());
        score.setSocialWork(scoreGeneralRequest.getSocialWork());
        score.setSocialContribution(scoreGeneralRequest.getSocialContribution());
        score.setPoliticActivity(scoreGeneralRequest.getPoliticActivity());
        score.setPublicity(scoreGeneralRequest.getPublicity());
        score.setSubAssessment(scoreGeneralRequest.getSubAssessment());
        score.setTotal(scoreGeneralRequest.getTotal());
        return score;
    }

    public static void mergeToEntity(Score score, ScoreGeneralRequest scoreUpdateRequest) {
        score.setConferActivity(scoreUpdateRequest.getConferActivity());
        score.setSocialWork(scoreUpdateRequest.getSocialWork());
        score.setSocialContribution(scoreUpdateRequest.getSocialContribution());
        score.setPoliticActivity(scoreUpdateRequest.getPoliticActivity());
        score.setPublicity(scoreUpdateRequest.getPublicity());
        score.setSubAssessment(scoreUpdateRequest.getSubAssessment());
        score.setTotal(scoreUpdateRequest.getTotal());
    }
}
