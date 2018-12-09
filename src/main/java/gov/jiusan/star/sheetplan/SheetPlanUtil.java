package gov.jiusan.star.sheetplan;

import gov.jiusan.star.score.ScoreUtil;
import gov.jiusan.star.sheet.Sheet;
import gov.jiusan.star.sheetplan.model.ReportDTO;
import gov.jiusan.star.sheetplan.model.SheetPlanDTO;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Marcus Lin
 */
class SheetPlanUtil {

    /**
     * 评级：不合格
     */
    private static final String RANK_UNQUALIFIED = "不合格";
    /**
     * 评级：合格
     */
    private static final String RANK_QUALIFIED = "合格";
    /**
     * 评级：三星级
     */
    private static final String RANK_LV_3 = "3 星级";
    /**
     * 评级：四星级
     */
    private static final String RANK_LV_4 = "4 星级";
    /**
     * 评级：五星级
     */
    private static final String RANK_LV_5 = "5 星级";

    static SheetPlanDTO convert(SheetPlan plan) {
        SheetPlanDTO model = new SheetPlanDTO();
        model.setSheetName(plan.getSheet().getName());
        model.setEffective(plan.isEffective());
        model.setEffectiveTime(plan.getEffectiveTime());
        model.setExpirationTime(plan.getExpirationTime());
        model.setAllScoreNum(plan.getScores().size());
        model.setFinishedScoreNum(((int) plan.getScores().stream().filter(ScoreUtil::isFinished).count()));
        model.setStatus(plan.getStatus());
        return model;
    }

    static ReportDTO convertToReport(SheetPlan plan) {
        int totalNum = plan.getScores().size();
        int completeNum = (int) plan.getScores().stream().filter(ScoreUtil::isFinished).count();
        int incompleteNum = totalNum - completeNum;
        int lv5Num = (int) plan.getScores().stream().filter(s -> ScoreUtil.computeRank(s) == ScoreUtil.RANK_LV_5).count();
        int lv4Num = (int) plan.getScores().stream().filter(s -> ScoreUtil.computeRank(s) == ScoreUtil.RANK_LV_4).count();
        int lv3Num = (int) plan.getScores().stream().filter(s -> ScoreUtil.computeRank(s) == ScoreUtil.RANK_LV_3).count();
        int qualifiedNum = (int) plan.getScores().stream().filter(s -> ScoreUtil.computeRank(s) == ScoreUtil.RANK_QUALIFIED).count();
        int unqualifiedNum = (int) plan.getScores().stream().filter(s -> ScoreUtil.computeRank(s) == ScoreUtil.RANK_UNQUALIFIED).count();
        ReportDTO report = new ReportDTO();
        report.setTotalNum(totalNum);
        report.setCompleteNum(completeNum);
        report.setIncompleteNum(incompleteNum);
        Map<String, Integer> levelInfo = new TreeMap<>();
        levelInfo.put(RANK_LV_5, lv5Num);
        levelInfo.put(RANK_LV_4, lv4Num);
        levelInfo.put(RANK_LV_3, lv3Num);
        levelInfo.put(RANK_QUALIFIED, qualifiedNum);
        levelInfo.put(RANK_UNQUALIFIED, unqualifiedNum);
        report.setLevelInfo(levelInfo);
        return report;
    }
}
