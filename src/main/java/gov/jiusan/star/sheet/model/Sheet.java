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

package gov.jiusan.star.sheet.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Marcus Lin
 */
public class Sheet implements Serializable {

    private Long seq;

    @NotBlank
    private String name;

    private String description;

    private Integer maxScore;

    @NotEmpty
    private List<@Valid Category> categories;

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

    public List<Category> getCategories() {
        return categories == null ? categories = new ArrayList<>() : categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
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

    public static class Category implements Serializable {

        private Long seq;

        @NotEmpty
        private String name;

        @NotNull
        @Positive
        private Integer maxScore;

        @NotEmpty
        private List<@Valid Item> items;

        public Category() {
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

        public List<Item> getItems() {
            return items == null ? items = new ArrayList<>() : items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }
    }

    public static class Item implements Serializable {

        private Long seq;

        @NotEmpty
        private String description;

        @NotNull
        @PositiveOrZero
        private Integer eachScore;

        @NotNull
        @Positive
        private Integer maxScore;

        public Item() {
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
