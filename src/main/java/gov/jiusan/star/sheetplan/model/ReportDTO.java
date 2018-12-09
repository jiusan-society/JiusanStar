package gov.jiusan.star.sheetplan.model;

import java.io.Serializable;
import java.util.Map;

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
     * 各评定等级对应的组织数量（Key is level name, Value is numbers of org)
     */
    private Map<String, Integer> levelInfo;


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

    public Map<String, Integer> getLevelInfo() {
        return levelInfo;
    }

    public void setLevelInfo(Map<String, Integer> levelInfo) {
        this.levelInfo = levelInfo;
    }
}
