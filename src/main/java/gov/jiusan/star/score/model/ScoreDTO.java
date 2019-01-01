/*
 * Copyright 2019 Marcus Lin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gov.jiusan.star.score.model;

import gov.jiusan.star.org.model.OrgDTO;
import gov.jiusan.star.sheetplan.model.SheetPlanDTO;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

/**
 * @author Marcus Lin
 */
public class ScoreDTO implements Serializable {

    private Long seq;

    private SheetPlanDTO plan;

    private OrgDTO org;

    private boolean sAFinished;

    private boolean aAFinished;

    private boolean finished;

    /**
     * K -> Details' Id
     * V -> Details' SA ScoreDTO
     */
    private Map<Long, @NotNull Integer> sADetails;

    /**
     * K -> Details' Id
     * V -> Details' AA ScoreDTO
     */
    private Map<Long, @NotNull Integer> aADetails;

    private Integer sATotalScore;

    private Integer aATotalScore;

    private Double finalScore;

    private Integer rank;

    public ScoreDTO() {
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public SheetPlanDTO getPlan() {
        return plan;
    }

    public void setPlan(SheetPlanDTO plan) {
        this.plan = plan;
    }

    public OrgDTO getOrg() {
        return org;
    }

    public void setOrg(OrgDTO org) {
        this.org = org;
    }

    public boolean issAFinished() {
        return sAFinished;
    }

    public void setsAFinished(boolean sAFinished) {
        this.sAFinished = sAFinished;
    }

    public boolean isaAFinished() {
        return aAFinished;
    }

    public void setaAFinished(boolean aAFinished) {
        this.aAFinished = aAFinished;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
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
