package gov.jiusan.star;

import gov.jiusan.star.user.UserCreateRequest;
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
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setOrgName("ywjczz");
        userCreateRequest.setUserName("linfaimom");
        userCreateRequest.setPassword("xxxx");
        client.target(API_ROOT)
                .path("user")
                .request()
                .post(Entity.json(userCreateRequest));
    }

}
