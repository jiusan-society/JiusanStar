package gov.jiusan.star;

import gov.jiusan.star.score.Score;
import gov.jiusan.star.score.ScoreService;
import gov.jiusan.star.score.ScoreUtil;
import gov.jiusan.star.score.api.CreateRequest;
import gov.jiusan.star.score.api.CreateResponse;
import gov.jiusan.star.score.api.DeleteResponse;
import gov.jiusan.star.score.api.RetrieveResponse;
import gov.jiusan.star.score.api.UpdateRequest;
import gov.jiusan.star.score.api.UpdateResponse;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Optional;

/**
 * @author Marcus Lin
 */
@Path("score")
@RequestScoped
public class ScoreServiceRest {

    @EJB
    private ScoreService scoreService;

    @POST
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

    @Path("{seq}")
    @GET
    public RetrieveResponse retrieveScore(@PathParam("seq") Long seq) {
        Optional<Score> scoreEntity = scoreService.find(seq);
        return scoreEntity.map(score -> RetrieveResponse.SUCCESS(ScoreUtil.convertToDTO(score))).orElse(RetrieveResponse.NO_SCORE);
    }

    @Path("{seq}")
    @PUT
    public UpdateResponse updateScore(UpdateRequest request, @PathParam("seq") Long seq) {
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

    @Path("{seq}")
    @DELETE
    public DeleteResponse deleteScore(@PathParam("seq") Long seq) {
        Optional<Score> score = scoreService.find(seq);
        if (!score.isPresent()) {
            return DeleteResponse.NO_SCORE;
        }
        scoreService.delete(score.get());
        return DeleteResponse.SUCCESS;
    }
}
