package gov.jiusan.star.sheet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Marcus Lin
 */
@Entity
@Table(name = "rating_details")
class RatingDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seq;

    /**
     * 指标细则的描述
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * 该指标的可得分
     */
    @Column(name = "each_score", nullable = false)
    private Integer eachScore;

    /**
     * 该指标细则的最高可得分
     */
    @Column(name = "max_score", nullable = false)
    private Integer maxScore;

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

    public Integer getEachScore() {
        return eachScore;
    }

    void setEachScore(Integer eachScore) {
        this.eachScore = eachScore;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }
}
