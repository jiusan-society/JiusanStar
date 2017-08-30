package gov.jiusan.star.score;

/**
 * @author Marcus Lin
 */
class ScoreUtil {

    static Score transferToEntity(gov.jiusan.star.score.api.Score scoreDTO) {
        Score score = new Score();
        score.setConferActivity(scoreDTO.getConferActivity());
        score.setSocialWork(scoreDTO.getSocialWork());
        score.setSocialContribution(scoreDTO.getSocialContribution());
        score.setPoliticActivity(scoreDTO.getPoliticActivity());
        score.setPublicity(scoreDTO.getPublicity());
        score.setSubAssessment(scoreDTO.getSubAssessment());
        score.setTotal(scoreDTO.getTotal());
        return score;
    }

    static gov.jiusan.star.score.api.Score transferToDTO(Score score) {
        gov.jiusan.star.score.api.Score scoreDTO = new gov.jiusan.star.score.api.Score();
        scoreDTO.setConferActivity(score.getConferActivity());
        scoreDTO.setSocialWork(score.getSocialWork());
        scoreDTO.setSocialContribution(score.getSocialContribution());
        scoreDTO.setPoliticActivity(score.getPoliticActivity());
        scoreDTO.setPublicity(score.getPublicity());
        scoreDTO.setSubAssessment(score.getSubAssessment());
        scoreDTO.setTotal(score.getTotal());
        return scoreDTO;
    }

    static void mergeToEntity(Score score, gov.jiusan.star.score.api.Score scoreUpdateRequest) {
        score.setConferActivity(scoreUpdateRequest.getConferActivity());
        score.setSocialWork(scoreUpdateRequest.getSocialWork());
        score.setSocialContribution(scoreUpdateRequest.getSocialContribution());
        score.setPoliticActivity(scoreUpdateRequest.getPoliticActivity());
        score.setPublicity(scoreUpdateRequest.getPublicity());
        score.setSubAssessment(scoreUpdateRequest.getSubAssessment());
        score.setTotal(scoreUpdateRequest.getTotal());
    }
}
