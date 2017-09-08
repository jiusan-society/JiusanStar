package gov.jiusan.star.score;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Calendar;

/**
 * @author Marcus Lin
 */
@Entity
@Table(name = "score")
@NamedQueries({
        @NamedQuery(name = "Score.findAll", query = "select s from Score s")
})
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq", nullable = false)
    private Long seq;

    /**
     * 会议活动得分，多个评选项，用 JSON 存放
     */
    @Column(name = "confer_activity", nullable = false)
    private String conferActivity;

    /**
     * 社务工作得分，多个评选项，用 JSON 存放
     */
    @Column(name = "social_work", nullable = false)
    private String socialWork;

    /**
     * 参政议政得分，多个评选项，用 JSON 存放
     */
    @Column(name = "politic_activity", nullable = false)
    private String politicActivity;

    /**
     * 社会服务得分，多个评选项，用 JSON 存放
     */
    @Column(name = "social_contribution", nullable = false)
    private String socialContribution;

    /**
     * 宣传报道得分
     */
    @Column(name = "publicity", nullable = false)
    private int publicity;

    /**
     * 下属支社年度考核结果得分
     */
    @Column(name = "sub_assessment", nullable = false)
    private int subAssessment;

    /**
     * 总分
     */
    @Column(name = "total", nullable = false)
    private int total;

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

    public Score() {
    }

    public Long getSeq() {
        return seq;
    }

    void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getConferActivity() {
        return conferActivity;
    }

    void setConferActivity(String conferActivity) {
        this.conferActivity = conferActivity;
    }

    public String getSocialWork() {
        return socialWork;
    }

    void setSocialWork(String socialWork) {
        this.socialWork = socialWork;
    }

    public String getPoliticActivity() {
        return politicActivity;
    }

    void setPoliticActivity(String politicActivity) {
        this.politicActivity = politicActivity;
    }

    public String getSocialContribution() {
        return socialContribution;
    }

    void setSocialContribution(String socialContribution) {
        this.socialContribution = socialContribution;
    }

    public int getPublicity() {
        return publicity;
    }

    void setPublicity(int publicity) {
        this.publicity = publicity;
    }

    public int getSubAssessment() {
        return subAssessment;
    }

    void setSubAssessment(int subAssessment) {
        this.subAssessment = subAssessment;
    }

    public int getTotal() {
        return total;
    }

    void setTotal(int total) {
        this.total = total;
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
