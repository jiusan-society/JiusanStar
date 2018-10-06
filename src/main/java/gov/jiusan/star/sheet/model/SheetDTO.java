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
public class SheetDTO implements Serializable {

    private Long seq;

    @NotEmpty
    @Size(min = 3, max = 20)
    private String name;

    private String description;

    private Integer maxScore;

    @NotEmpty
    private List<PhaseDTO> phaseDTOs;

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

    public List<PhaseDTO> getPhaseDTOs() {
        return phaseDTOs == null ? phaseDTOs = new ArrayList<>() : phaseDTOs;
    }

    public void setPhaseDTOs(List<PhaseDTO> phaseDTOs) {
        this.phaseDTOs = phaseDTOs;
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

    public static class PhaseDTO implements Serializable {

        private Long seq;
        private String name;
        private Integer maxScore;
        private List<DetailsDTO> detailsDTOs;

        public PhaseDTO() {
        }

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

        public Integer getMaxScore() {
            return maxScore;
        }

        public void setMaxScore(Integer maxScore) {
            this.maxScore = maxScore;
        }

        public List<DetailsDTO> getDetailsDTOs() {
            return detailsDTOs == null ? detailsDTOs = new ArrayList<>() : detailsDTOs;
        }

        public void setDetailsDTOs(List<DetailsDTO> detailsDTOs) {
            this.detailsDTOs = detailsDTOs;
        }
    }

    public static class DetailsDTO implements Serializable {

        private Long seq;
        private String description;
        private Integer eachScore;
        private Integer maxScore;

        public DetailsDTO() {
        }

        public Long getSeq() {
            return seq;
        }

        public void setSeq(Long seq) {
            this.seq = seq;
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
    }
}
