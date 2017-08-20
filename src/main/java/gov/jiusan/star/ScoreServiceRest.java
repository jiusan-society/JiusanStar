package gov.jiusan.star;

import gov.jiusan.star.score.ScoreCreateResponse;
import gov.jiusan.star.score.ScoreRequest;
import gov.jiusan.star.score.ScoreService;
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
    public ScoreCreateResponse createScore(ScoreRequest scoreRequest) {
        return scoreService.createScore(scoreRequest);
    }

    @Path("score/{seq}")
    @PUT
    public ScoreUpdateResponse updateScore(ScoreRequest scoreRequest, @PathParam("seq") Long seq) {
        return scoreService.updateScore(scoreRequest, seq);
    }
}
