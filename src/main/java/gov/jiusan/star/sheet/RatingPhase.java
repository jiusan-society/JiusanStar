package gov.jiusan.star.sheet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * @author Marcus Lin
 */
@Entity
@Table(name = "rating_phase")
class RatingPhase implements Serializable {

    @Id
    private Long seq;

    /**
     * 指标大类的名称
     */
    @Column(name = "name", nullable = false)
    private String name;


    /**
     * 该指标大类的用户最高可得分
     */
    @Column(name = "max_score", nullable = false)
    private Integer maxScore;

    /**
     * 指标细则
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "phase_seq")
    private List<RatingDetails> ratingDetails;

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false)
    private Calendar createTime;

    /**
     * 更新时间
     */
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

    public Integer getMaxScore() {
        return maxScore;
    }

    void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public List<RatingDetails> getRatingDetails() {
        return ratingDetails;
    }

    void setRatingDetails(List<RatingDetails> ratingDetails) {
        this.ratingDetails = ratingDetails;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public Calendar getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Calendar lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
