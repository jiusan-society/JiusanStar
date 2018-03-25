package gov.jiusan.star.sheet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Marcus Lin
 */
@Entity
@Table(name = "rating_details")
class RatingDetails implements Serializable {

    @Id
    private Long seq;

    /**
     * 指标细则的描述
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * 该指标细则的最高可得分
     */
    @Column(name = "max_score", nullable = false)
    private Integer maxScore;

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

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
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
