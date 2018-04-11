package gov.jiusan.star.org;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Marcus Lin
 */
@Entity
@Table(name = "app_user")
class User implements Serializable {

    @Id
    @TableGenerator(
        name = "USER_SEQ_GENERATOR",
        table = "star_seq_gen",
        pkColumnName = "seq_name",
        pkColumnValue = "USER_SEQ",
        valueColumnName = "seq_value",
        initialValue = 50
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_SEQ_GENERATOR")
    private Long seq;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "account", nullable = false)
    private String account;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number")
    private String phoneNum;

    @Column(name = "email")
    private String email;

    @Column(name = "create_time", nullable = false)
    private Calendar createTime;

    @Column(name = "create_time", nullable = false)
    private Calendar lastUpdateTime;

    public Long getSeq() {
        return seq;
    }

    void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccount() {
        return account;
    }

    void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public Calendar getLastUpdateTime() {
        return lastUpdateTime;
    }

    void setLastUpdateTime(Calendar lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
