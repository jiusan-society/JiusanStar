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
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "js_org")
public class Org implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "org")
    private List<Score> score;

    public Org() {
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Org getParent() {
        return parent;
    }

    public void setParent(Org parent) {
        this.parent = parent;
    }

    public Org getRoot() {
        return root;
    }

    public void setRoot(Org root) {
        this.root = root;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Score> getScore() {
        return score;
    }

    public void setScore(List<Score> score) {
        this.score = score;
    }
}
