package gov.jiusan.star.sheetplan;

import gov.jiusan.star.score.ScoreUtil;
import gov.jiusan.star.sheetplan.model.SheetPlanDTO;

/**
 * @author Marcus Lin
 */
class SheetPlanUtil {

    static SheetPlanDTO convert(SheetPlan plan) {
        var model = new SheetPlanDTO();
        model.setSheetName(plan.getSheet().getName());
        model.setEffective(plan.isEffective());
        model.setEffectiveTime(plan.getEffectiveTime());
        model.setExpirationTime(plan.getExpirationTime());
        model.setAllScoreNum(plan.getScores().size());
        model.setFinishedScoreNum(((int) plan.getScores().stream().filter(ScoreUtil::isFinished).count()));
        model.setStatus(plan.getStatus());
        return model;
    }
}
