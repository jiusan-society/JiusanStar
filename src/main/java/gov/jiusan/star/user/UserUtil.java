package gov.jiusan.star.user;

import gov.jiusan.star.util.PasswordStorage;

/**
 * @author Marcus Lin
 */
public class UserUtil {

    public static User toEntity(UserCreateRequest userCreateRequest) {
        User user = new User();
        user.setUserName(userCreateRequest.getUserName());
        user.setOrgName(userCreateRequest.getOrgName());
        try {
            user.setPassword(PasswordStorage.createHash(userCreateRequest.getPassword()));
        } catch (PasswordStorage.CannotPerformOperationException e) {
            e.printStackTrace();
        }
        return user;
    }

}
