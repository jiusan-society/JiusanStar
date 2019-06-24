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

package gov.jiusan.star.score;

import gov.jiusan.star.org.Org;
import gov.jiusan.star.sheetplan.SheetPlan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 测评得分情况表
 *
 * @author Marcus Lin
 */
@Entity
@Table(name = "js_score")
public class Score implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seq", nullable = false)
    private Long seq;

    /**
     * 关联组织
     */
    @ManyToOne
    @JoinColumn(name = "org_seq")
    private Org org;

    @ManyToOne
    @JoinColumn(name = "sheet_plan_seq")
    private SheetPlan sheetPlan;

    /**
     * 是否完成自评
     */
    @Column(name = "sa_finished", nullable = false)
    private boolean sAFinished;

    /**
     * 是否完成考评
     */
    @Column(name = "aa_finished", nullable = false)
    private boolean aAFinished;

    /**
     * 自评得分详情 sA -> selfAssessment
     */
    @Column(name = "sa_details")
    private String sADetails;

    /**
     * 考评得分详情 aA -> adminAssessment
     */
    @Column(name = "aa_details")
    private String aADetails;

    /**
     * 自评总得分
     */
    @Column(name = "sa_total_score")
    private Integer sATotalScore;

    /**
     * 考评总得分
     */
    @Column(name = "aa_total_score")
    private Integer aATotalScore;

    /**
     * 最终得分
     */
    @Column(name = "final_score")
    private Double finalScore;

    /**
     * 创建时间
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false)
    private Calendar createTime;

    /**
     * 更新时间
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "last_update_time", nullable = false)
    private Calendar lastUpdateTime;

    public Score() {
    }

    public Long getSeq() {
        return seq;
    }

    void setSeq(Long seq) {
        this.seq = seq;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
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

    public String getsADetails() {
        return sADetails;
    }

    public void setsADetails(String sADetails) {
        this.sADetails = sADetails;
    }

    public String getaADetails() {
        return aADetails;
    }

    public void setaADetails(String aADetails) {
        this.aADetails = aADetails;
    }

    public Integer getsATotalScore() {
        return sATotalScore == null ? sATotalScore = 0 : sATotalScore;
    }

    public void setsATotalScore(Integer sATotalScore) {
        this.sATotalScore = sATotalScore;
    }

    public Integer getaATotalScore() {
        return aATotalScore == null ? aATotalScore = 0 : aATotalScore;
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

    public Calendar getCreateTime() {
        return createTime;
    }

    void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public Calendar getLastUpdateTime() {
        return lastUpdateTime;
    }

    void setLastUpdateTime(Calendar lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public SheetPlan getSheetPlan() {
        return sheetPlan;
    }

    public void setSheetPlan(SheetPlan sheetPlan) {
        this.sheetPlan = sheetPlan;
    }
}
