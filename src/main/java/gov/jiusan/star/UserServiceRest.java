package gov.jiusan.star;

import gov.jiusan.star.user.UserCreateRequest;
import gov.jiusan.star.user.UserCreateResponse;
import gov.jiusan.star.user.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author Marcus Lin
 */

@Path("/")
@RequestScoped
public class UserServiceRest {

    @EJB
    private UserService userService;

    @Path("user")
    @POST
    public UserCreateResponse createUser(UserCreateRequest userCreateRequest) {
        return userService.createUser(userCreateRequest);
    }

}
