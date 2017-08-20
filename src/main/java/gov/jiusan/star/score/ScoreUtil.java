package gov.jiusan.star.score;

/**
 * @author Marcus Lin
 */
public class ScoreUtil {

    public static Score transferToEntity(ScoreCreateRequest scoreCreateRequest) {
        Score score = new Score();
        score.setConferActivity(scoreCreateRequest.getConferActivity());
        score.setSocialWork(scoreCreateRequest.getSocialWork());
        score.setSocialContribution(scoreCreateRequest.getSocialContribution());
        score.setPoliticActivity(scoreCreateRequest.getPoliticActivity());
        score.setPublicity(scoreCreateRequest.getPublicity());
        score.setSubAssessment(scoreCreateRequest.getSubAssessment());
        score.setTotal(scoreCreateRequest.getTotal());
        return score;
    }

    public static void mergeToEntity(Score score, ScoreUpdateRequest scoreUpdateRequest) {
        score.setConferActivity(scoreUpdateRequest.getConferActivity());
        score.setSocialWork(scoreUpdateRequest.getSocialWork());
        score.setSocialContribution(scoreUpdateRequest.getSocialContribution());
        score.setPoliticActivity(scoreUpdateRequest.getPoliticActivity());
        score.setPublicity(scoreUpdateRequest.getPublicity());
        score.setSubAssessment(scoreUpdateRequest.getSubAssessment());
        score.setTotal(scoreUpdateRequest.getTotal());
    }
}
