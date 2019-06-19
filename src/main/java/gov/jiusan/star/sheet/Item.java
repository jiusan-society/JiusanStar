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

package gov.jiusan.star.sheet;

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
 * 指标大类中的指标细则
 *
 * @author Marcus Lin
 */
@Entity
@Table(name = "item")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seq;

    @ManyToOne
    @JoinColumn(name = "sheet_seq")
    private Sheet sheet;

    @ManyToOne
    @JoinColumn(name = "category_seq")
    private Category category;

    /**
     * 指标细则的描述
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * 该指标的可得分
     */
    @Column(name = "each_score", nullable = false)
    private Integer eachScore;

    /**
     * 该指标细则的最高可得分
     */
    @Column(name = "max_score", nullable = false)
    private Integer maxScore;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false)
    private Calendar createTime;

    /**
     * 更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_time", nullable = false)
    private Calendar lastUpdateTime;

    public Long getSeq() {
        return seq;
    }

    void setSeq(Long seq) {
        this.seq = seq;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public Integer getEachScore() {
        return eachScore;
    }

    void setEachScore(Integer eachScore) {
        this.eachScore = eachScore;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
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
