package gov.jiusan.star.user;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private String orgName;
    private String userName;

    public UserDTO() {}

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
