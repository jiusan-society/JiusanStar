package gov.jiusan.star.sheetplan;

import gov.jiusan.star.score.ScoreUtil;
import gov.jiusan.star.sheetplan.model.ReportDTO;
import gov.jiusan.star.sheetplan.model.SheetPlanDTO;

/**
 * @author Marcus Lin
 */
class SheetPlanUtil {

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
        report.setLv5Num(lv5Num);
        report.setLv4Num(lv4Num);
        report.setLv3Num(lv3Num);
        report.setQualifiedNum(qualifiedNum);
        report.setUnqualifiedNum(unqualifiedNum);
        return report;
    }
}
