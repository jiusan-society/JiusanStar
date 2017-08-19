package gov.jiusan.star;

import gov.jiusan.star.score.ScoreCreateRequest;
import gov.jiusan.star.score.ScoreService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

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
    public Long createScore(ScoreCreateRequest scoreCreateRequest) {
        return scoreService.createScore(scoreCreateRequest);
    }
}
