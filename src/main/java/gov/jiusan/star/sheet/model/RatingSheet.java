package gov.jiusan.star.sheet.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Marcus Lin
 */
public class RatingSheet implements Serializable {

    private Long seq;

    @NotEmpty
    @Size(min = 3, max = 20)
    private String name;
    private String description;

    @NotEmpty
    private List<RatingPhase> ratingPhases;

    private Calendar createTime;
    private Calendar lastUpdateTime;

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
