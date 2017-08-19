package gov.jiusan.star.score;

/**
 * @author Marcus Lin
 */
public class ScoreUtil {

    public static Score toEntity(ScoreCreateRequest scoreCreateRequest) {
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
}
