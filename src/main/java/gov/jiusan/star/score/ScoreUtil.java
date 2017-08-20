package gov.jiusan.star.score;

/**
 * @author Marcus Lin
 */
public class ScoreUtil {

    public static Score transferToEntity(ScoreRequest scoreRequest) {
        Score score = new Score();
        score.setConferActivity(scoreRequest.getConferActivity());
        score.setSocialWork(scoreRequest.getSocialWork());
        score.setSocialContribution(scoreRequest.getSocialContribution());
        score.setPoliticActivity(scoreRequest.getPoliticActivity());
        score.setPublicity(scoreRequest.getPublicity());
        score.setSubAssessment(scoreRequest.getSubAssessment());
        score.setTotal(scoreRequest.getTotal());
        return score;
    }

    public static void mergeToEntity(Score score, ScoreRequest scoreUpdateRequest) {
        score.setConferActivity(scoreUpdateRequest.getConferActivity());
        score.setSocialWork(scoreUpdateRequest.getSocialWork());
        score.setSocialContribution(scoreUpdateRequest.getSocialContribution());
        score.setPoliticActivity(scoreUpdateRequest.getPoliticActivity());
        score.setPublicity(scoreUpdateRequest.getPublicity());
        score.setSubAssessment(scoreUpdateRequest.getSubAssessment());
        score.setTotal(scoreUpdateRequest.getTotal());
    }
}
