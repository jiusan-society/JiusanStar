package gov.jiusan.star.score.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcus Lin
 */
public class Score implements Serializable {

    private String sheetName;
    private String sheetDesc;
    private List<Phase> phases;

    public Score() {
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getSheetDesc() {
        return sheetDesc;
    }

    public void setSheetDesc(String sheetDesc) {
        this.sheetDesc = sheetDesc;
    }

    public List<Phase> getPhases() {
        return phases;
    }

    public void setPhases(List<Phase> phases) {
        this.phases = phases;
    }

    public static class Phase implements Serializable {

        private String name;
        private Integer maxScore;
        private List<Details> details;

        public Phase() {
        }

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

        @NotNull
        private Integer sAScore;

        @NotNull
        private Integer aAScore;

        public Details() {
        }

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

        @NotNull
        public Integer getsAScore() {
            return sAScore;
        }

        public void setsAScore(@NotNull Integer sAScore) {
            this.sAScore = sAScore;
        }

        @NotNull
        public Integer getaAScore() {
            return aAScore;
        }

        public void setaAScore(@NotNull Integer aAScore) {
            this.aAScore = aAScore;
        }
    }
}
