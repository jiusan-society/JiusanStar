package gov.jiusan.star.user;

import gov.jiusan.star.util.PasswordStorage;

/**
 * @author Marcus Lin
 */
public class UserUtil {

    public static User toEntity(UserRequest userRequest) {
        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setOrgName(userRequest.getOrgName());
        try {
            user.setPassword(PasswordStorage.createHash(userRequest.getPassword()));
        } catch (PasswordStorage.CannotPerformOperationException e) {
            e.printStackTrace();
        }
        return user;
    }

}
