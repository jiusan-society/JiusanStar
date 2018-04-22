package gov.jiusan.star.org;

import gov.jiusan.star.score.Score;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "app_org")
public class Org implements Serializable {

    @Id
    @TableGenerator(
        name = "ORG_SEQ_GENERATOR",
        table = "star_seq_gen",
        pkColumnName = "seq_name",
        pkColumnValue = "ORG_SEQ",
        valueColumnName = "seq_value",
        initialValue = 50
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ORG_SEQ_GENERATOR")
    private Long seq;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_seq")
    private Org parent;

    @ManyToOne
    @JoinColumn(name = "root_seq")
    private Org root;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "org", orphanRemoval = true)
    private List<User> users;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "org", orphanRemoval = true)
    private Score score;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false)
    private Calendar createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_time", nullable = false)
    private Calendar lastUpdateTime;

    public Long getSeq() {
        return seq;
    }

    void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public Org getParent() {
        return parent;
    }

    void setParent(Org parent) {
        this.parent = parent;
    }

    public Org getRoot() {
        return root;
    }

    void setRoot(Org root) {
        this.root = root;
    }

    public List<User> getUsers() {
        return users;
    }

    void setUsers(List<User> users) {
        this.users = users;
    }

    public Score getScore() {
        return score;
    }

    void setScore(Score score) {
        this.score = score;
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
