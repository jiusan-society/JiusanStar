package gov.jiusan.star.init.model;

import java.io.Serializable;

/**
 * @author Marcus Lin
 */
public class Org implements Serializable {

    private String name;
    private String code;
    private String parentCode;
    private String rootCode;
    private String adminUserName;
    private String adminUserAccount;
    private String adminUserPassword;

    public Org() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return "".equals(parentCode) ? null : parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getAdminUserAccount() {
        return adminUserAccount;
    }

    public void setAdminUserAccount(String adminUserAccount) {
        this.adminUserAccount = adminUserAccount;
    }

    public String getAdminUserPassword() {
        return adminUserPassword;
    }

    public void setAdminUserPassword(String adminUserPassword) {
        this.adminUserPassword = adminUserPassword;
    }

    public String getRootCode() {
        return "".equals(rootCode) ? null : rootCode;
    }

    public void setRootCode(String rootCode) {
        this.rootCode = rootCode;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }
}
