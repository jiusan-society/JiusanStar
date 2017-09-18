package gov.jiusan.star.score;

import gov.jiusan.star.score.api.CreateRequest;
import gov.jiusan.star.score.api.CreateResponse;
import gov.jiusan.star.score.api.DeleteResponse;
import gov.jiusan.star.score.api.RetrieveResponse;
import gov.jiusan.star.score.api.UpdateRequest;
import gov.jiusan.star.score.api.UpdateResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Marcus Lin
 */
@RequestMapping(path = "api/score")
@RestController
public class ScoreRestController {

    @Autowired
    private ScoreService scoreService;

    @RequestMapping(method = RequestMethod.POST)
    public CreateResponse createScore(CreateRequest request) {
        if (StringUtils.isEmpty(request.getConferActivity())) {
            return CreateResponse.NO_CONFER_ACTIVITY_SCORE;
        }
        if (StringUtils.isEmpty(request.getSocialWork())) {
            return CreateResponse.NO_SOCIAL_WORK_SCORE;
        }
        if (StringUtils.isEmpty(request.getSocialContribution())) {
            return CreateResponse.NO_SOCIAL_CONTRIBUTION_SCORE;
        }
        if (StringUtils.isEmpty(request.getPoliticActivity())) {
            return CreateResponse.NO_POLITIC_ACTIVITY_SCORE;
        }

        scoreService.create(request);

        return CreateResponse.SUCCESS;
    }

    @RequestMapping(path = "{seq}", method = RequestMethod.GET)
    public RetrieveResponse retrieveScore(@PathVariable("seq") Long seq) {
        Optional<Score> scoreEntity = scoreService.find(seq);
        return scoreEntity.map(score -> RetrieveResponse.SUCCESS(ScoreUtil.convertToDTO(score))).orElse(RetrieveResponse.NO_SCORE);
    }

    @RequestMapping(path = "{seq}", method = RequestMethod.PUT)
    public UpdateResponse updateScore(UpdateRequest request, @PathVariable("seq") Long seq) {
        if (StringUtils.isEmpty(request.getConferActivity())) {
            return UpdateResponse.NO_CONFER_ACTIVITY_SCORE;
        }
        if (StringUtils.isEmpty(request.getSocialWork())) {
            return UpdateResponse.NO_SOCIAL_WORK_SCORE;
        }
        if (StringUtils.isEmpty(request.getSocialContribution())) {
            return UpdateResponse.NO_SOCIAL_CONTRIBUTION_SCORE;
        }
        if (StringUtils.isEmpty(request.getPoliticActivity())) {
            return UpdateResponse.NO_POLITIC_ACTIVITY_SCORE;
        }

        Optional<Score> score = scoreService.find(seq);

        if (!score.isPresent()) {
            return UpdateResponse.NO_SCORE;
        }

        Score updatedScore = scoreService.update(request, score.get());

        return UpdateResponse.SUCCESS(ScoreUtil.convertToDTO(updatedScore));
    }

    @RequestMapping(path = "{seq}", method = RequestMethod.DELETE)
    public DeleteResponse deleteScore(@PathVariable("seq") Long seq) {
        Optional<Score> score = scoreService.find(seq);
        if (!score.isPresent()) {
            return DeleteResponse.NO_SCORE;
        }
        scoreService.delete(score.get());
        return DeleteResponse.SUCCESS;
    }
}
