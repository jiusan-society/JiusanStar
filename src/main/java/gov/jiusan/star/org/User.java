package gov.jiusan.star.org;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @Column(name = "user_name", nullable = false)
    private String username;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public enum SexType {
        MALE,
        FEMALE,
        UNKNOWN
    }
}
