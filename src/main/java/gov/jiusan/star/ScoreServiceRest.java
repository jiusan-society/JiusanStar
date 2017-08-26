package gov.jiusan.star;

import gov.jiusan.star.score.ScoreService;
import gov.jiusan.star.score.api.CreateResponse;
import gov.jiusan.star.score.api.DeleteResponse;
import gov.jiusan.star.score.api.GeneralRequest;
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
    public CreateResponse createScore(GeneralRequest generalRequest) {
        return scoreService.createScore(generalRequest);
    }

    @Path("score/{seq}")
    @GET
    public RetrieveResponse retrieveScore(@PathParam("seq") Long seq) {
        return scoreService.retrieveScore(seq);
    }

    @Path("score/{seq}")
    @PUT
    public UpdateResponse updateScore(GeneralRequest generalRequest, @PathParam("seq") Long seq) {
        return scoreService.updateScore(generalRequest, seq);
    }

    @Path("score/{seq}")
    @DELETE
    public DeleteResponse deleteScore(@PathParam("seq") Long seq) {
        return scoreService.deleteScore(seq);
    }
}
