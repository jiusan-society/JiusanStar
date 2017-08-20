package gov.jiusan.star.user;

import gov.jiusan.star.util.PasswordUtil;

/**
 * @author Marcus Lin
 */
public class UserUtil {

    public static User toEntity(UserCreateRequest userCreateRequest) {
        User user = new User();
        user.setUserName(userCreateRequest.getUserName());
        user.setOrgName(userCreateRequest.getOrgName());
        try {
            user.setPassword(PasswordUtil.createHash(userCreateRequest.getPassword()));
        } catch (PasswordUtil.CannotPerformOperationException e) {
            e.printStackTrace();
        }
        return user;
    }

}
