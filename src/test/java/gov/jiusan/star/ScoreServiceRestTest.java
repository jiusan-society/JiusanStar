package gov.jiusan.star;

import gov.jiusan.star.score.api.CreateResponse;
import gov.jiusan.star.score.api.DeleteResponse;
import gov.jiusan.star.score.api.GeneralRequest;
import gov.jiusan.star.score.api.RetrieveResponse;
import gov.jiusan.star.score.api.Status;
import gov.jiusan.star.score.api.UpdateResponse;
import gov.jiusan.star.score.detail.ConferActivity;
import gov.jiusan.star.score.detail.PoliticActivity;
import gov.jiusan.star.score.detail.SocialContribution;
import gov.jiusan.star.score.detail.SocialWork;
import gov.jiusan.star.util.JacksonUtil;
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
    public void testCreateScore() {
        GeneralRequest generalRequest = new GeneralRequest();
        ConferActivity conferActivity = new ConferActivity();
        conferActivity.setScore1(10);
        conferActivity.setScore2(10);
        conferActivity.setScore3(30);
        // TODO[2017-08-20][Marcus Lin]: 需要对转化 JSON 的操作做一个封装
        generalRequest.setConferActivity(JacksonUtil.toString(conferActivity).get());
        PoliticActivity politicActivity = new PoliticActivity();
        politicActivity.setScore1(10);
        politicActivity.setScore2(10);
        generalRequest.setPoliticActivity(JacksonUtil.toString(politicActivity).get());
        SocialWork socialWork = new SocialWork();
        socialWork.setScore1(20);
        generalRequest.setSocialWork(JacksonUtil.toString(socialWork).get());
        SocialContribution socialContribution = new SocialContribution();
        socialContribution.setScore1(12);
        generalRequest.setSocialContribution(JacksonUtil.toString(socialContribution).get());
        generalRequest.setPublicity(10);
        generalRequest.setSubAssessment(20);
        CreateResponse createResponse = client.target(API_ROOT)
                .path("score")
                .request()
                .post(Entity.json(generalRequest), CreateResponse.class);
        Assert.assertEquals(true, createResponse.isSuccess());
        Assert.assertEquals(Status.SUCCESS, createResponse.getScoreResponseStatus());
    }

    @Test
    public void testRetrieveScore() {
        RetrieveResponse retrieveResponse = client.target(API_ROOT)
                .path("score/{seq}")
                .resolveTemplate("seq", 1)
                .request()
                .get(RetrieveResponse.class);
        Assert.assertEquals(true, retrieveResponse.isSuccess());
        Assert.assertEquals(Status.SUCCESS, retrieveResponse.getStatus());
        Assert.assertNotEquals("", retrieveResponse.getContent().getConferActivity());
        Assert.assertNotEquals("", retrieveResponse.getContent().getPoliticActivity());
        Assert.assertNotEquals("", retrieveResponse.getContent().getSocialWork());
        Assert.assertNotEquals("", retrieveResponse.getContent().getSocialContribution());
        Assert.assertEquals(10, retrieveResponse.getContent().getPublicity());
        Assert.assertEquals(20, retrieveResponse.getContent().getSubAssessment());
    }

    @Test
    public void testUpdateScore() {
        GeneralRequest generalRequest = new GeneralRequest();
        ConferActivity conferActivity = new ConferActivity();
        conferActivity.setScore1(1);
        conferActivity.setScore2(2);
        conferActivity.setScore3(3);
        generalRequest.setConferActivity(JacksonUtil.toString(conferActivity).get());
        PoliticActivity politicActivity = new PoliticActivity();
        politicActivity.setScore1(4);
        politicActivity.setScore2(5);
        generalRequest.setPoliticActivity(JacksonUtil.toString(politicActivity).get());
        SocialWork socialWork = new SocialWork();
        socialWork.setScore1(20);
        generalRequest.setSocialWork(JacksonUtil.toString(socialWork).get());
        SocialContribution socialContribution = new SocialContribution();
        socialContribution.setScore1(23);
        generalRequest.setSocialContribution(JacksonUtil.toString(socialContribution).get());
        generalRequest.setPublicity(10);
        generalRequest.setSubAssessment(20);
        UpdateResponse updateResponse = client.target(API_ROOT)
                .path("score/{seq}")
                .resolveTemplate("seq", 1)
                .request()
                .put(Entity.json(generalRequest), UpdateResponse.class);
        Assert.assertEquals(true, updateResponse.isSuccess());
        Assert.assertEquals(Status.SUCCESS, updateResponse.getScoreResponseStatus());
        Assert.assertNotEquals("", updateResponse.getContent().getConferActivity());
        Assert.assertNotEquals("", updateResponse.getContent().getPoliticActivity());
        Assert.assertNotEquals("", updateResponse.getContent().getSocialWork());
        Assert.assertNotEquals("", updateResponse.getContent().getSocialContribution());
        Assert.assertEquals(10, updateResponse.getContent().getPublicity());
        Assert.assertEquals(20, updateResponse.getContent().getSubAssessment());
    }

    @Test
    public void testDeleteScore() {
        DeleteResponse deleteResponse = client.target(API_ROOT)
                .path("score/{seq}")
                .resolveTemplate("seq", 1)
                .request()
                .delete(DeleteResponse.class);
        Assert.assertEquals(true, deleteResponse.isSuccess());
        Assert.assertEquals(Status.SUCCESS, deleteResponse.getStatus());
    }

}