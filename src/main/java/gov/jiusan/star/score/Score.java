package gov.jiusan.star.score;

import gov.jiusan.star.org.Org;
import gov.jiusan.star.sheet.RatingSheet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Marcus Lin
 */
@Entity
@Table(name = "score")
public class Score implements Serializable {

    @Id
    @TableGenerator(
        name = "SCORE_SEQ_GENERATOR",
        table = "star_seq_gen",
        pkColumnName = "seq_name",
        pkColumnValue = "SCORE_SEQ",
        valueColumnName = "seq_value",
        initialValue = 50
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SCORE_SEQ_GENERATOR")
    @Column(name = "seq", nullable = false)
    private Long seq;

    /**
     * 关联组织
     */
    @ManyToOne
    @JoinColumn(name = "org_seq")
    private Org org;

    @ManyToOne
    @JoinColumn(name = "sheet_seq")
    private RatingSheet sheet;

    /**
     * 是否生效
     */
    @Column(name = "effective", nullable = false)
    private boolean effective;

    /**
     * 自评得分详情 sA -> selfAssessment
     */
    @Column(name = "sa_details")
    private String sADetails;

    /**
     * 考评得分详情 aA -> adminAssessment
     */
    @Column(name = "aa_details")
    private String aADetails;

    /**
     * 自评总得分
     */
    @Column(name = "sa_total_score")
    private Integer sATotalScore;

    /**
     * 考评总得分
     */
    @Column(name = "aa_total_score")
    private Integer aATotalScore;

    /**
     * 最终得分
     */
    @Column(name = "final_score")
    private Integer finalScore;

    /**
     * 失效时间
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "expiration_time")
    private Calendar expirationTime;

    /**
     * 创建时间
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false)
    private Calendar createTime;

    /**
     * 更新时间
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "last_update_time", nullable = false)
    private Calendar lastUpdateTime;

    public Score() {
    }

    public Long getSeq() {
        return seq;
    }

    void setSeq(Long seq) {
        this.seq = seq;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public RatingSheet getSheet() {
        return sheet;
    }

    public void setSheet(RatingSheet sheet) {
        this.sheet = sheet;
    }

    public boolean isEffective() {
        return effective;
    }

    public void setEffective(boolean effective) {
        this.effective = effective;
    }

    public String getsADetails() {
        return sADetails;
    }

    public void setsADetails(String sADetails) {
        this.sADetails = sADetails;
    }

    public String getaADetails() {
        return aADetails;
    }

    public void setaADetails(String aADetails) {
        this.aADetails = aADetails;
    }

    public Integer getsATotalScore() {
        return sATotalScore;
    }

    public void setsATotalScore(Integer sATotalScore) {
        this.sATotalScore = sATotalScore;
    }

    public Integer getaATotalScore() {
        return aATotalScore;
    }

    public void setaATotalScore(Integer aATotalScore) {
        this.aATotalScore = aATotalScore;
    }

    public Integer getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Integer finalScore) {
        this.finalScore = finalScore;
    }

    public Calendar getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Calendar expirationTime) {
        this.expirationTime = expirationTime;
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
