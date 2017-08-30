package gov.jiusan.star.user;

import org.apache.commons.lang3.StringUtils;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Marcus Lin
 */
@Stateless
@DeclareRoles({"ADMIN", "USER"})
public class UserService {

    @PersistenceContext
    private EntityManager em;

    @RolesAllowed("ADMIN")
    public UserCreateResponse createUser(UserCreateRequest userCreateRequest) {
        if (StringUtils.isEmpty(userCreateRequest.getUserName())) {
            return UserCreateResponse.USER_NAME_ERROR;
        }
        if (StringUtils.isEmpty(userCreateRequest.getOrgName())) {
            return UserCreateResponse.ORG_NAME_ERROR;
        }
        if (StringUtils.isEmpty(userCreateRequest.getPassword())) {
            return UserCreateResponse.PASSWORD_ERROR;
        }

        User user = UserUtil.toEntity(userCreateRequest);
        em.persist(user);
        return UserCreateResponse.SUCCESS(user.getSeq());
    }
}
