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
public class Sheet implements Serializable {

    private Long seq;

    @NotEmpty
    @Size(min = 3, max = 20)
    private String name;

    private String description;

    private Integer maxScore;

    @NotEmpty
    private List<Phase> phases;

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

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public List<Phase> getPhases() {
        return phases == null ? phases = new ArrayList<>() : phases;
    }

    public void setPhases(List<Phase> phases) {
        this.phases = phases;
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

    public static class Phase implements Serializable {

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

    public static class Details implements Serializable {

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
}
