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
    private ConferActivity conferActivity;

    // 社务工作得分，多个评选项，用 JSON 存放
    private SocialWork socialWork;

    // 参政议政得分，多个评选项，用 JSON 存放
    private PoliticActivity politicActivity;

    // 社会服务得分，多个评选项，用 JSON 存放
    private SocialContribution socialContribution;

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

    public ConferActivity getConferActivity() {
        return conferActivity;
    }

    public void setConferActivity(ConferActivity conferActivity) {
        this.conferActivity = conferActivity;
    }

    public SocialWork getSocialWork() {
        return socialWork;
    }

    public void setSocialWork(SocialWork socialWork) {
        this.socialWork = socialWork;
    }

    public PoliticActivity getPoliticActivity() {
        return politicActivity;
    }

    public void setPoliticActivity(PoliticActivity politicActivity) {
        this.politicActivity = politicActivity;
    }

    public SocialContribution getSocialContribution() {
        return socialContribution;
    }

    public void setSocialContribution(SocialContribution socialContribution) {
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
