package gov.jiusan.star.user;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/")
@Stateless
public class UserService {

    @Inject
    private EntityManager em;

    @Path("user")
    @POST
    public Long createUser() {
        return 1L;
    }

}
