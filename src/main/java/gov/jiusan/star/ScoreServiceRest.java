package gov.jiusan.star;

import gov.jiusan.star.score.ScoreCreateResponse;
import gov.jiusan.star.score.ScoreDeleteResponse;
import gov.jiusan.star.score.ScoreGeneralRequest;
import gov.jiusan.star.score.ScoreRetrieveResponse;
import gov.jiusan.star.score.ScoreService;
import gov.jiusan.star.score.ScoreUpdateResponse;

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
    public ScoreCreateResponse createScore(ScoreGeneralRequest scoreGeneralRequest) {
        return scoreService.createScore(scoreGeneralRequest);
    }

    @Path("score/{seq}")
    @GET
    public ScoreRetrieveResponse retrieveScore(@PathParam("seq") Long seq) {
        return scoreService.retrieveScore(seq);
    }

    @Path("score/{seq}")
    @PUT
    public ScoreUpdateResponse updateScore(ScoreGeneralRequest scoreGeneralRequest, @PathParam("seq") Long seq) {
        return scoreService.updateScore(scoreGeneralRequest, seq);
    }

    @Path("score/{seq}")
    @DELETE
    public ScoreDeleteResponse deleteScore(@PathParam("seq") Long seq) {
        return scoreService.deleteScore(seq);
    }
}
