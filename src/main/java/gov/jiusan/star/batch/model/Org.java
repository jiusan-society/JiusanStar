package gov.jiusan.star.batch.model;

import java.io.Serializable;
import java.util.List;

public class Org implements Serializable {

    private String name;
    private String code;
    private String parentCode;
    private boolean root;
    private List<Org> subOrgs;
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
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public List<Org> getSubOrgs() {
        return subOrgs;
    }

    public void setSubOrgs(List<Org> subOrgs) {
        this.subOrgs = subOrgs;
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

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }
}
