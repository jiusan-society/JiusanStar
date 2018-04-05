package gov.jiusan.star.sheet.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Marcus Lin
 */
public class RatingPhase implements Serializable {

    private String name;
    private Integer maxScore;
    private List<RatingDetails> ratingDetails;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public List<RatingDetails> getRatingDetails() {
        return ratingDetails;
    }

    public void setRatingDetails(List<RatingDetails> ratingDetails) {
        this.ratingDetails = ratingDetails;
    }
}
