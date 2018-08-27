package gov.jiusan.star.sheet.model;

import java.io.Serializable;

/**
 * @author Marcus Lin
 */
public class Details implements Serializable {

    private String description;
    private Integer eachScore;
    private Integer maxScore;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEachScore() {
        return eachScore;
    }

    public void setEachScore(Integer eachScore) {
        this.eachScore = eachScore;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }
}
