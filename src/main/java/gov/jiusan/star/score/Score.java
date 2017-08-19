package gov.jiusan.star.score;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Marcus Lin
 */

@Entity
@Table(name = "score")
class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long seq;

    // 会议活动得分，多个评选项，用 JSON 存放
    @Column(name = "confer_activity_score")
    private String conferActivityScore;

    // 社务工作得分，多个评选项，用 JSON 存放
    @Column(name = "social_work_score")
    private String socialWorkScore;

    // 参政议政得分，多个评选项，用 JSON 存放
    @Column(name = "politic_involved_score")
    private String politicInvolvedScore;

    // 社会服务得分，多个评选项，用 JSON 存放
    @Column(name = "social_service_score")
    private String socialServiceScore;

    // 宣传报道得分
    @Column(name = "publicity_score")
    private int publicityScore;

    // 下属支社年度考核结果得分
    @Column(name = "sub_accessment_score")
    private int subAccessmentScore;

    // 总分
    @Column(name = "total_score")
    private int totalScore;

    public Score() {
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getConferActivityScore() {
        return conferActivityScore;
    }

    public void setConferActivityScore(String conferActivityScore) {
        this.conferActivityScore = conferActivityScore;
    }

    public String getSocialWorkScore() {
        return socialWorkScore;
    }

    public void setSocialWorkScore(String socialWorkScore) {
        this.socialWorkScore = socialWorkScore;
    }

    public String getPoliticInvolvedScore() {
        return politicInvolvedScore;
    }

    public void setPoliticInvolvedScore(String politicInvolvedScore) {
        this.politicInvolvedScore = politicInvolvedScore;
    }

    public String getSocialServiceScore() {
        return socialServiceScore;
    }

    public void setSocialServiceScore(String socialServiceScore) {
        this.socialServiceScore = socialServiceScore;
    }

    public int getPublicityScore() {
        return publicityScore;
    }

    public void setPublicityScore(int publicityScore) {
        this.publicityScore = publicityScore;
    }

    public int getSubAccessmentScore() {
        return subAccessmentScore;
    }

    public void setSubAccessmentScore(int subAccessmentScore) {
        this.subAccessmentScore = subAccessmentScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
