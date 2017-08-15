package gov.jiusan.star.user;

import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserService {

    @PersistenceContext
    private EntityManager em;

    public CreateUserResponse createUser(UserRequest userRequest) {
        if (StringUtils.isEmpty(userRequest.getUserName())) {
            return CreateUserResponse.USER_NAME_ERROR;
        }
        if (StringUtils.isEmpty(userRequest.getOrgName())) {
            return CreateUserResponse.ORG_NAME_ERROR;
        }
        if (StringUtils.isEmpty(userRequest.getPassword())) {
            return CreateUserResponse.PASSWORD_ERROR;
        }

        User user = UserUtil.toEntity(userRequest);
        em.persist(user);
        return CreateUserResponse.SUCCESS(user.getSeq());
    }
}
