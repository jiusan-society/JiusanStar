package gov.jiusan.star.util;

import gov.jiusan.star.score.detail.ConferActivity;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class JacksonUtilTest {

    @Test
    public void testToString() throws Exception {
        ConferActivity conferActivity = new ConferActivity();
        conferActivity.setScore1(10);
        conferActivity.setScore2(20);
        conferActivity.setScore3(30);
        String jsonString = JacksonUtil.toString(conferActivity).get();
        Assert.assertNotNull(jsonString);
        System.out.println(jsonString);
    }

    @Test
    public void testToObj() throws Exception {
        String jsonString = "{\"score1\":10,\"score2\":20,\"score3\":30}";
        ConferActivity conferActivity = JacksonUtil.toObj(jsonString, ConferActivity.class).get();
        Assert.assertEquals(10, conferActivity.getScore1());
        Assert.assertEquals(20, conferActivity.getScore2());
        Assert.assertEquals(30, conferActivity.getScore3());
    }

}