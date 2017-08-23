package gov.jiusan.star.score;

import gov.jiusan.star.score.api.GeneralRequest;

/**
 * @author Marcus Lin
 */
public class ScoreUtil {

    public static Score transferToEntity(GeneralRequest generalRequest) {
        Score score = new Score();
        score.setConferActivity(generalRequest.getConferActivity());
        score.setSocialWork(generalRequest.getSocialWork());
        score.setSocialContribution(generalRequest.getSocialContribution());
        score.setPoliticActivity(generalRequest.getPoliticActivity());
        score.setPublicity(generalRequest.getPublicity());
        score.setSubAssessment(generalRequest.getSubAssessment());
        score.setTotal(generalRequest.getTotal());
        return score;
    }

    public static void mergeToEntity(Score score, GeneralRequest scoreUpdateRequest) {
        score.setConferActivity(scoreUpdateRequest.getConferActivity());
        score.setSocialWork(scoreUpdateRequest.getSocialWork());
        score.setSocialContribution(scoreUpdateRequest.getSocialContribution());
        score.setPoliticActivity(scoreUpdateRequest.getPoliticActivity());
        score.setPublicity(scoreUpdateRequest.getPublicity());
        score.setSubAssessment(scoreUpdateRequest.getSubAssessment());
        score.setTotal(scoreUpdateRequest.getTotal());
    }
}
