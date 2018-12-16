package gov.jiusan.star.score.model;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Marcus Lin
 */
public class ScoreDTO implements Serializable {

    private Long seq;

    private String orgName;

    private boolean sAFinished;

    private boolean aAFinished;

    private boolean finished;

    /**
     * K -> Details' Id
     * V -> Details' SA ScoreDTO
     */
    private Map<Long, Integer> sADetails;

    /**
     * K -> Details' Id
     * V -> Details' AA ScoreDTO
     */
    private Map<Long, Integer> aADetails;

    private Integer sATotalScore;

    private Integer aATotalScore;

    private Double finalScore;

    private Integer rank;

    public ScoreDTO() {
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public boolean issAFinished() {
        return sAFinished;
    }

    public void setsAFinished(boolean sAFinished) {
        this.sAFinished = sAFinished;
    }

    public boolean isaAFinished() {
        return aAFinished;
    }

    public void setaAFinished(boolean aAFinished) {
        this.aAFinished = aAFinished;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Map<Long, Integer> getsADetails() {
        return sADetails;
    }

    public void setsADetails(Map<Long, Integer> sADetails) {
        this.sADetails = sADetails;
    }

    public Map<Long, Integer> getaADetails() {
        return aADetails;
    }

    public void setaADetails(Map<Long, Integer> aADetails) {
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

    public Double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
