package gov.jiusan.star.sheet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Marcus Lin
 */
@Entity
@Table(name = "rating_sheet")
class RatingSheet implements Serializable {

    @Id
    @TableGenerator(
        name = "SHEET_SEQ_GENERATOR",
        table = "star_seq_gen",
        pkColumnName = "seq_name",
        pkColumnValue = "SHEET_SEQ",
        valueColumnName = "seq_value",
        initialValue = 50
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SHEET_SEQ_GENERATOR")
    private Long seq;

    /**
     * 评分表名称
     */
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "effective", nullable = false)
    private boolean effective;

    /**
     * 评分表描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 该评分表的最高可得分
     */
    @Column(name = "max_score", nullable = false)
    private Integer maxScore = 100;

    /**
     * 评分大类
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sheet_seq")
    private List<RatingPhase> ratingPhases;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false)
    private Calendar createTime;

    /**
     * 更新时间
     */
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

    public boolean isEffective() {
        return effective;
    }

    void setEffective(boolean effective) {
        this.effective = effective;
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

    public List<RatingPhase> getRatingPhases() {
        return ratingPhases == null ? ratingPhases = new ArrayList<>() : ratingPhases;
    }

    void setRatingPhases(List<RatingPhase> ratingPhases) {
        this.ratingPhases = ratingPhases;
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
