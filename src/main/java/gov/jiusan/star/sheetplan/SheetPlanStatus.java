package gov.jiusan.star.sheetplan;

/**
 * @author Marcus Lin
 */
public enum SheetPlanStatus {
    /**
     * 正常可用状态
     */
    NORMAL,
    /**
     * 超过截止时间后变为逾期失效状态
     */
    EXPIRED,
    /**
     * 同一年下仅可有一张可用的 SheetPlan，其他需变为不可用状态
     */
    INVALID
}
