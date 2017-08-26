package gov.jiusan.star.user;

import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Marcus Lin
 */
@Stateless
public class UserService {

    @PersistenceContext
    private EntityManager em;

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
