package gov.jiusan.star;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.jiusan.star.score.ScoreCreateResponse;
import gov.jiusan.star.score.ScoreDeleteResponse;
import gov.jiusan.star.score.ScoreGeneralRequest;
import gov.jiusan.star.score.ScoreResponseStatus;
import gov.jiusan.star.score.ScoreRetrieveResponse;
import gov.jiusan.star.score.ScoreUpdateResponse;
import gov.jiusan.star.score.detail.ConferActivity;
import gov.jiusan.star.score.detail.PoliticActivity;
import gov.jiusan.star.score.detail.SocialContribution;
import gov.jiusan.star.score.detail.SocialWork;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

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
        ScoreGeneralRequest scoreGeneralRequest = new ScoreGeneralRequest();
        ConferActivity conferActivity = new ConferActivity();
        conferActivity.setScore1(10);
        conferActivity.setScore2(10);
        conferActivity.setScore3(30);
        // TODO[2017-08-20][Marcus Lin]: 需要对转化 JSON 的操作做一个封装
        scoreGeneralRequest.setConferActivity(new ObjectMapper().writeValueAsString(conferActivity));
        PoliticActivity politicActivity = new PoliticActivity();
        politicActivity.setScore1(10);
        politicActivity.setScore2(10);
        scoreGeneralRequest.setPoliticActivity(new ObjectMapper().writeValueAsString(politicActivity));
        SocialWork socialWork = new SocialWork();
        socialWork.setScore1(20);
        scoreGeneralRequest.setSocialWork(new ObjectMapper().writeValueAsString(socialWork));
        SocialContribution socialContribution = new SocialContribution();
        socialContribution.setScore1(12);
        scoreGeneralRequest.setSocialContribution(new ObjectMapper().writeValueAsString(socialContribution));
        scoreGeneralRequest.setPublicity(10);
        scoreGeneralRequest.setSubAssessment(20);
        ScoreCreateResponse scoreCreateResponse = client.target(API_ROOT)
                .path("score")
                .request()
                .post(Entity.json(scoreGeneralRequest), ScoreCreateResponse.class);
        Assert.assertEquals(true, scoreCreateResponse.isSuccess());
        Assert.assertEquals(ScoreResponseStatus.SUCCESS, scoreCreateResponse.getScoreResponseStatus());
    }

    @Test
    public void testRetrieveScore() {
        ScoreRetrieveResponse scoreRetrieveResponse = client.target(API_ROOT)
                .path("score/{seq}")
                .resolveTemplate("seq", 1)
                .request()
                .get(ScoreRetrieveResponse.class);
        Assert.assertEquals(true, scoreRetrieveResponse.isSuccess());
        Assert.assertEquals(ScoreResponseStatus.SUCCESS, scoreRetrieveResponse.getStatus());
        Assert.assertNotEquals("", scoreRetrieveResponse.getContent().getConferActivity());
        Assert.assertNotEquals("", scoreRetrieveResponse.getContent().getPoliticActivity());
        Assert.assertNotEquals("", scoreRetrieveResponse.getContent().getSocialWork());
        Assert.assertNotEquals("", scoreRetrieveResponse.getContent().getSocialContribution());
        Assert.assertEquals(10, scoreRetrieveResponse.getContent().getPublicity());
        Assert.assertEquals(20, scoreRetrieveResponse.getContent().getSubAssessment());
    }

    @Test
    public void testUpdateScore() throws JsonProcessingException {
        ScoreGeneralRequest scoreGeneralRequest = new ScoreGeneralRequest();
        ConferActivity conferActivity = new ConferActivity();
        conferActivity.setScore1(1);
        conferActivity.setScore2(2);
        conferActivity.setScore3(3);
        scoreGeneralRequest.setConferActivity(new ObjectMapper().writeValueAsString(conferActivity));
        PoliticActivity politicActivity = new PoliticActivity();
        politicActivity.setScore1(4);
        politicActivity.setScore2(5);
        scoreGeneralRequest.setPoliticActivity(new ObjectMapper().writeValueAsString(politicActivity));
        SocialWork socialWork = new SocialWork();
        socialWork.setScore1(20);
        scoreGeneralRequest.setSocialWork(new ObjectMapper().writeValueAsString(socialWork));
        SocialContribution socialContribution = new SocialContribution();
        socialContribution.setScore1(23);
        scoreGeneralRequest.setSocialContribution(new ObjectMapper().writeValueAsString(socialContribution));
        scoreGeneralRequest.setPublicity(10);
        scoreGeneralRequest.setSubAssessment(20);
        ScoreUpdateResponse scoreUpdateResponse = client.target(API_ROOT)
                .path("score/{seq}")
                .resolveTemplate("seq", 1)
                .request()
                .put(Entity.json(scoreGeneralRequest), ScoreUpdateResponse.class);
        Assert.assertEquals(true, scoreUpdateResponse.isSuccess());
        Assert.assertEquals(ScoreResponseStatus.SUCCESS, scoreUpdateResponse.getScoreResponseStatus());
        Assert.assertNotEquals("", scoreUpdateResponse.getContent().getConferActivity());
        Assert.assertNotEquals("", scoreUpdateResponse.getContent().getPoliticActivity());
        Assert.assertNotEquals("", scoreUpdateResponse.getContent().getSocialWork());
        Assert.assertNotEquals("", scoreUpdateResponse.getContent().getSocialContribution());
        Assert.assertEquals(10, scoreUpdateResponse.getContent().getPublicity());
        Assert.assertEquals(20, scoreUpdateResponse.getContent().getSubAssessment());
    }

    @Test
    public void testDeleteScore() {
        ScoreDeleteResponse scoreDeleteResponse = client.target(API_ROOT)
                .path("score/{seq}")
                .resolveTemplate("seq", 1)
                .request()
                .delete(ScoreDeleteResponse.class);
        Assert.assertEquals(true, scoreDeleteResponse.isSuccess());
        Assert.assertEquals(ScoreResponseStatus.SUCCESS, scoreDeleteResponse.getStatus());
    }

}