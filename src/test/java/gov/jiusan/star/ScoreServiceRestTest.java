package gov.jiusan.star;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.jiusan.star.score.ScoreCreateResponse;
import gov.jiusan.star.score.ScoreRequest;
import gov.jiusan.star.score.ScoreResponseStatus;
import gov.jiusan.star.score.ScoreUpdateResponse;
import gov.jiusan.star.score.detail.ConferActivity;
import gov.jiusan.star.score.detail.PoliticActivity;
import gov.jiusan.star.score.detail.SocialContribution;
import gov.jiusan.star.score.detail.SocialWork;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

/**
 * @author Marcus Lin
 */
public class ScoreServiceRestTest {

    private Client client;
    private static final String API_ROOT = "http://localhost:8080/JiusanStar/rs/";

    @Before
    public void init() {
        client = ClientBuilder.newClient();
    }

    @After
    public void destroy() {
        client.close();
    }

    @Test
    public void testCreateScore() throws JsonProcessingException {
        ScoreRequest scoreRequest = new ScoreRequest();
        ConferActivity conferActivity = new ConferActivity();
        conferActivity.setScore1(10);
        conferActivity.setScore2(10);
        conferActivity.setScore3(30);
        // TODO[2017-08-20][Marcus Lin]: 需要对转化 JSON 的操作做一个封装
        scoreRequest.setConferActivity(new ObjectMapper().writeValueAsString(conferActivity));
        PoliticActivity politicActivity = new PoliticActivity();
        politicActivity.setScore1(10);
        politicActivity.setScore2(10);
        scoreRequest.setPoliticActivity(new ObjectMapper().writeValueAsString(politicActivity));
        SocialWork socialWork = new SocialWork();
        socialWork.setScore1(20);
        scoreRequest.setSocialWork(new ObjectMapper().writeValueAsString(socialWork));
        SocialContribution socialContribution = new SocialContribution();
        socialContribution.setScore1(12);
        scoreRequest.setSocialContribution(new ObjectMapper().writeValueAsString(socialContribution));
        scoreRequest.setPublicity(10);
        scoreRequest.setSubAssessment(20);
        ScoreCreateResponse scoreCreateResponse = client.target(API_ROOT)
                .path("score")
                .request()
                .post(Entity.json(scoreRequest), ScoreCreateResponse.class);
        Assert.assertEquals(true, scoreCreateResponse.isSuccess());
        Assert.assertEquals(ScoreResponseStatus.SUCCESS, scoreCreateResponse.getScoreResponseStatus());
    }

    @Test
    public void testUpdateScore() throws JsonProcessingException {
        ScoreRequest scoreRequest = new ScoreRequest();
        ConferActivity conferActivity = new ConferActivity();
        conferActivity.setScore1(1);
        conferActivity.setScore2(2);
        conferActivity.setScore3(3);
        scoreRequest.setConferActivity(new ObjectMapper().writeValueAsString(conferActivity));
        PoliticActivity politicActivity = new PoliticActivity();
        politicActivity.setScore1(4);
        politicActivity.setScore2(5);
        scoreRequest.setPoliticActivity(new ObjectMapper().writeValueAsString(politicActivity));
        SocialWork socialWork = new SocialWork();
        socialWork.setScore1(20);
        scoreRequest.setSocialWork(new ObjectMapper().writeValueAsString(socialWork));
        SocialContribution socialContribution = new SocialContribution();
        socialContribution.setScore1(23);
        scoreRequest.setSocialContribution(new ObjectMapper().writeValueAsString(socialContribution));
        scoreRequest.setPublicity(10);
        scoreRequest.setSubAssessment(20);
        ScoreUpdateResponse scoreUpdateResponse = client.target(API_ROOT)
                .path("score/{seq}")
                .resolveTemplate("seq", 1)
                .request()
                .put(Entity.json(scoreRequest), ScoreUpdateResponse.class);
        Assert.assertEquals(true, scoreUpdateResponse.isSuccess());
        Assert.assertEquals(ScoreResponseStatus.SUCCESS, scoreUpdateResponse.getScoreResponseStatus());
        Assert.assertEquals(10, scoreUpdateResponse.getContent().getPublicity());
        Assert.assertEquals(20, scoreUpdateResponse.getContent().getSubAssessment());
    }

}