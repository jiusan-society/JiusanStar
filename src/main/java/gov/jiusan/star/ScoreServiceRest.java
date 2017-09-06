package gov.jiusan.star;

import gov.jiusan.star.score.ScoreService;
import gov.jiusan.star.score.api.CreateRequest;
import gov.jiusan.star.score.api.CreateResponse;
import gov.jiusan.star.score.api.DeleteRequest;
import gov.jiusan.star.score.api.DeleteResponse;
import gov.jiusan.star.score.api.RetrieveRequest;
import gov.jiusan.star.score.api.RetrieveResponse;
import gov.jiusan.star.score.api.UpdateRequest;
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
    public CreateResponse createScore(CreateRequest request) {
        return scoreService.createScore(request);
    }

    @Path("score/{seq}")
    @GET
    public RetrieveResponse retrieveScore(@PathParam("seq") Long seq) {
        RetrieveRequest request = new RetrieveRequest();
        request.setSeq(seq);
        return scoreService.retrieveScore(request);
    }

    @Path("score/{seq}")
    @PUT
    public UpdateResponse updateScore(UpdateRequest request, @PathParam("seq") Long seq) {
        request.setSeq(seq);
        return scoreService.updateScore(request);
    }

    @Path("score/{seq}")
    @DELETE
    public DeleteResponse deleteScore(@PathParam("seq") Long seq) {
        DeleteRequest request = new DeleteRequest();
        request.setSeq(seq);
        return scoreService.deleteScore(request);
    }
}
