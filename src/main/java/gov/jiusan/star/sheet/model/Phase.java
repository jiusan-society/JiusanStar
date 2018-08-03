package gov.jiusan.star.sheet.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcus Lin
 */
public class Phase implements Serializable {

    private String name;
    private Integer maxScore;
    private List<Details> details;

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

    public List<Details> getDetails() {
        return details == null ? details = new ArrayList<>() : details;
    }

    public void setDetails(List<Details> details) {
        this.details = details;
    }
}
