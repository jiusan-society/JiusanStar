package gov.jiusan.star;

import gov.jiusan.star.score.ScoreService;
import gov.jiusan.star.score.api.Score;
import gov.jiusan.star.score.api.GeneralResponse;
import gov.jiusan.star.score.api.RetrieveResponse;
import gov.jiusan.star.score.api.UpdateResponse;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * @author Marcus Lin
 */
@Path("/")
@RequestScoped
public class ScoreServiceRest {

    @EJB
    private ScoreService scoreService;

    @Path("score")
    @POST
    public GeneralResponse createScore(Score score) {
        return scoreService.createScore(score);
    }

    @Path("score/{seq}")
    @GET
    public RetrieveResponse retrieveScore(@PathParam("seq") Long seq) {
        return scoreService.retrieveScore(seq);
    }

    @Path("score/{seq}")
    @PUT
    public UpdateResponse updateScore(Score score, @PathParam("seq") Long seq) {
        return scoreService.updateScore(score, seq);
    }

    @Path("score/{seq}")
    @DELETE
    public GeneralResponse deleteScore(@PathParam("seq") Long seq) {
        return scoreService.deleteScore(seq);
    }
}
