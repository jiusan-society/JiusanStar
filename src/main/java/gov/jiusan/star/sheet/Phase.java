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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcus Lin
 */
@Entity
@Table(name = "phase")
public class Phase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seq;

    /**
     * 指标大类的名称
     */
    @Column(name = "name", nullable = false)
    private String name;


    /**
     * 该指标大类的最高可得分
     */
    @Column(name = "max_score", nullable = false)
    private Integer maxScore;

    /**
     * 指标细则
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "phase_seq")
    private List<Details> details;

    public Long getSeq() {
        return seq;
    }

    void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public List<Details> getDetails() {
        return details == null ? details = new ArrayList<>() : details;
    }

    void setDetails(List<Details> details) {
        this.details = details;
    }
}
