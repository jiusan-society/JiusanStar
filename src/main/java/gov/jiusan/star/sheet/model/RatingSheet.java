package gov.jiusan.star.sheet.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcus Lin
 */
public class RatingSheet implements Serializable {

    private Long seq;
    private String name;
    private String description;
    private List<RatingPhase> ratingPhases;

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RatingPhase> getRatingPhases() {
        return ratingPhases == null ? ratingPhases = new ArrayList<>() : ratingPhases;
    }

    public void setRatingPhases(List<RatingPhase> ratingPhases) {
        this.ratingPhases = ratingPhases;
    }
}
