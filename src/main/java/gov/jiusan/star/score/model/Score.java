package gov.jiusan.star.score.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Marcus Lin
 */
public class Score implements Serializable {

    // Score 流水号
    private Long seq;

    // 会议活动得分，多个评选项，用 JSON 存放
    private String conferActivity;

    // 社务工作得分，多个评选项，用 JSON 存放
    private String socialWork;

    // 参政议政得分，多个评选项，用 JSON 存放
    private String politicActivity;

    // 社会服务得分，多个评选项，用 JSON 存放
    private String socialContribution;

    // 宣传报道得分
    private int publicity;

    // 下属支社年度考核结果得分
    private int subAssessment;

    // 总分
    private int total;

    private Calendar createTime;

    private Calendar lastUpdateTime;

    public Score() {

    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getConferActivity() {
        return conferActivity;
    }

    public void setConferActivity(String conferActivity) {
        this.conferActivity = conferActivity;
    }

    public String getSocialWork() {
        return socialWork;
    }

    public void setSocialWork(String socialWork) {
        this.socialWork = socialWork;
    }

    public String getPoliticActivity() {
        return politicActivity;
    }

    public void setPoliticActivity(String politicActivity) {
        this.politicActivity = politicActivity;
    }

    public String getSocialContribution() {
        return socialContribution;
    }

    public void setSocialContribution(String socialContribution) {
        this.socialContribution = socialContribution;
    }

    public int getPublicity() {
        return publicity;
    }

    public void setPublicity(int publicity) {
        this.publicity = publicity;
    }

    public int getSubAssessment() {
        return subAssessment;
    }

    public void setSubAssessment(int subAssessment) {
        this.subAssessment = subAssessment;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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
