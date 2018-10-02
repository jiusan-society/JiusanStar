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
}
