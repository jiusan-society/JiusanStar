package gov.jiusan.star;

import gov.jiusan.star.score.ScoreCreateRequest;
import gov.jiusan.star.score.ScoreCreateResponse;
import gov.jiusan.star.score.ScoreService;
import gov.jiusan.star.score.ScoreUpdateRequest;
import gov.jiusan.star.score.ScoreUpdateResponse;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
    public ScoreCreateResponse createScore(ScoreCreateRequest scoreCreateRequest) {
        return scoreService.createScore(scoreCreateRequest);
    }

    @Path("score/{seq}")
    @PUT
    public ScoreUpdateResponse updateScore(ScoreUpdateRequest scoreUpdateRequest, @PathParam("seq") Long seq) {
        return scoreService.updateScore(scoreUpdateRequest, seq);
    }
}
