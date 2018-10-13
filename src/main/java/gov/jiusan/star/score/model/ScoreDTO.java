package gov.jiusan.star.score.model;

import java.io.Serializable;
import java.util.Map;

public class ScoreDTO implements Serializable {

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
