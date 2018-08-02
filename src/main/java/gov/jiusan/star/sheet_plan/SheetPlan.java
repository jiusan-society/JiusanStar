package gov.jiusan.star.sheet_plan;

import gov.jiusan.star.score.Score;
import gov.jiusan.star.sheet.Sheet;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "sheet_plan")
public class SheetPlan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seq;

    @Column(name = "effective")
    private boolean effective;

    @Column(name = "finish_rate")
    private double finishRate;

    @ManyToOne
    @JoinColumn(name = "sheet_seq")
    private Sheet sheet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sheetPlan", orphanRemoval = true)
    private List<Score> scores;

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

    /**
     * 完成时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "finish_time", nullable = false)
    private Calendar finishTime;

    /**
     * 生效时间
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "effective_time", nullable = false)
    private Calendar effectiveTime;

    /**
     * 过期时间
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "expiration_time", nullable = false)
    private Calendar expirationTime;

    public SheetPlan() {
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
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

    public Calendar getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Calendar finishTime) {
        this.finishTime = finishTime;
    }

    public Calendar getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Calendar effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Calendar getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Calendar expirationTime) {
        this.expirationTime = expirationTime;
    }

    public boolean isEffective() {
        return effective;
    }

    public void setEffective(boolean effective) {
        this.effective = effective;
    }

    public double getFinishRate() {
        return finishRate;
    }

    public void setFinishRate(double finishRate) {
        this.finishRate = finishRate;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
