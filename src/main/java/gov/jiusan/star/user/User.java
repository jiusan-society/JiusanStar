package gov.jiusan.star.user;

import gov.jiusan.star.org.Org;
import gov.jiusan.star.org.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Marcus Lin
 */
@Entity
@Table(name = "app_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "nick_name", nullable = false)
    private String nickname;

    @Enumerated
    @Column(name = "sex_type")
    private SexType sexType;

    @Column(name = "account", nullable = false)
    private String account;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number")
    private String phoneNum;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "org_seq")
    private Org org;

    @ManyToOne
    @JoinColumn(name = "role_seq")
    private Role role;

    public User() {
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public SexType getSexType() {
        return sexType == null ? sexType = SexType.UNKNOWN : sexType;
    }

    public void setSexType(SexType sexType) {
        this.sexType = sexType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public enum SexType {
        MALE,
        FEMALE,
        UNKNOWN
    }
}
