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

import gov.jiusan.star.sheetplan.SheetPlan;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Marcus Lin
 */
public class SheetPlanDTO implements Serializable {

    private String sheetName;
    private boolean effective;
    private Calendar effectiveTime;
    private Calendar expirationTime;
    private int finishedScoreNum;
    private int allScoreNum;
    private SheetPlan.Status status;

    public SheetPlanDTO() {
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public boolean isEffective() {
        return effective;
    }

    public void setEffective(boolean effective) {
        this.effective = effective;
    }

    public Calendar getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Calendar effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Calendar getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Calendar expirationTime) {
        this.expirationTime = expirationTime;
    }

    public int getFinishedScoreNum() {
        return finishedScoreNum;
    }

    public void setFinishedScoreNum(int finishedScoreNum) {
        this.finishedScoreNum = finishedScoreNum;
    }

    public int getAllScoreNum() {
        return allScoreNum;
    }

    public void setAllScoreNum(int allScoreNum) {
        this.allScoreNum = allScoreNum;
    }

    public SheetPlan.Status getStatus() {
        return status;
    }

    public void setStatus(SheetPlan.Status status) {
        this.status = status;
    }
}
