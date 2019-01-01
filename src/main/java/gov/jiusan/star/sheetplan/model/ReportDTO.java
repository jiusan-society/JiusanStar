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

package gov.jiusan.star.sheetplan.model;

import java.io.Serializable;

/**
 * @author Marcus Lin
 */
public class ReportDTO implements Serializable {

    /**
     * 组织总数
     */
    private int totalNum;
    /**
     * 已完成评分的组织数
     */
    private int completeNum;
    /**
     * 尚未完成评分的组织数
     */
    private int incompleteNum;
    /**
     * 5 星级的组织数
     */
    private int lv5Num;
    /**
     * 4 星级的组织数
     */
    private int lv4Num;
    /**
     * 3 星级的组织数
     */
    private int lv3Num;
    /**
     * 合格的组织数
     */
    private int qualifiedNum;
    /**
     * 不合格的组织数
     */
    private int unqualifiedNum;

    public ReportDTO() {
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(int completeNum) {
        this.completeNum = completeNum;
    }

    public int getIncompleteNum() {
        return incompleteNum;
    }

    public void setIncompleteNum(int incompleteNum) {
        this.incompleteNum = incompleteNum;
    }

    public int getLv5Num() {
        return lv5Num;
    }

    public void setLv5Num(int star5Num) {
        this.lv5Num = star5Num;
    }

    public int getLv4Num() {
        return lv4Num;
    }

    public void setLv4Num(int lv4Num) {
        this.lv4Num = lv4Num;
    }

    public int getLv3Num() {
        return lv3Num;
    }

    public void setLv3Num(int lv3Num) {
        this.lv3Num = lv3Num;
    }

    public int getQualifiedNum() {
        return qualifiedNum;
    }

    public void setQualifiedNum(int qualifiedNum) {
        this.qualifiedNum = qualifiedNum;
    }

    public int getUnqualifiedNum() {
        return unqualifiedNum;
    }

    public void setUnqualifiedNum(int unqualifiedNum) {
        this.unqualifiedNum = unqualifiedNum;
    }
}
