package gov.jiusan.star;

import gov.jiusan.star.user.UserRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

/**
 * @author Marcus Lin
 */
public class UserServiceRestTest {

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
    public void testCreateUser() {
        UserRequest userRequest = new UserRequest();
        userRequest.setOrgName("ywjczz");
        userRequest.setUserName("linfaimom");
        userRequest.setPassword("linfai88");
        client.target(API_ROOT)
                .path("user")
                .request()
                .post(Entity.json(userRequest));
    }

}
