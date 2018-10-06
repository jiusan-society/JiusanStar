package gov.jiusan.star.sheetplan.model;

import gov.jiusan.star.sheetplan.SheetPlanStatus;

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
    private SheetPlanStatus status;

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

    public SheetPlanStatus getStatus() {
        return status;
    }

    public void setStatus(SheetPlanStatus status) {
        this.status = status;
    }
}
