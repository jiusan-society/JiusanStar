package gov.jiusan.star.user;

import gov.jiusan.star.user.model.Profile;

public class UserUtil {

    static Profile convertToModel(User user) {
        Profile p = new Profile();
        p.setAccount(user.getAccount());
        p.setNickName(user.getNickname());
        p.setOrgName(user.getOrg().getName());
        p.setRoleName(user.getRole().getName());
        p.setPhoneNum(user.getPhoneNum());
        p.setEmail(user.getEmail());
        return p;
    }

}
