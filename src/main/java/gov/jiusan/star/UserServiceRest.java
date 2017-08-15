package gov.jiusan.star;

import gov.jiusan.star.user.CreateUserResponse;
import gov.jiusan.star.user.UserRequest;
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
    public CreateUserResponse createUser(UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

}
