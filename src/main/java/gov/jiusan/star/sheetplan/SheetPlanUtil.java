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

package gov.jiusan.star.sheetplan;

import gov.jiusan.star.score.Score;
import gov.jiusan.star.score.ScoreUtil;
import gov.jiusan.star.sheetplan.model.ReportDTO;
import gov.jiusan.star.sheetplan.model.SheetPlanDTO;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Marcus Lin
 */
public class SheetPlanUtil {

    public static SheetPlanDTO convert(SheetPlan plan) {
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
        List<Score> scores = plan.getScores();
        int totalNum = scores.size();
        int completeNum = getCounts(scores, ScoreUtil::isFinished);
        int incompleteNum = totalNum - completeNum;
        int lv5Num = getCounts(scores, s -> ScoreUtil.computeRank(s) == ScoreUtil.RANK_LV_5);
        int lv4Num = getCounts(scores, s -> ScoreUtil.computeRank(s) == ScoreUtil.RANK_LV_4);
        int lv3Num = getCounts(scores, s -> ScoreUtil.computeRank(s) == ScoreUtil.RANK_LV_3);
        int qualifiedNum = getCounts(scores, s -> ScoreUtil.computeRank(s) == ScoreUtil.RANK_QUALIFIED);
        int unqualifiedNum = getCounts(scores, s -> ScoreUtil.computeRank(s) == ScoreUtil.RANK_UNQUALIFIED);
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

    private static int getCounts(List<Score> scores, Predicate<Score> predicate) {
        return (int) scores.stream().filter(predicate).count();
    }

    public static boolean isExpired(SheetPlan plan) {
        return SheetPlanStatus.EXPIRED == plan.getStatus();
    }
}
