package gov.jiusan.star.score;

import gov.jiusan.star.sheet.Details;
import gov.jiusan.star.sheet.Phase;
import gov.jiusan.star.sheet.Sheet;

import java.util.stream.Collectors;

class ScoreUtil {

    static gov.jiusan.star.score.model.Score convert(Score score) {
        gov.jiusan.star.score.model.Score model = new gov.jiusan.star.score.model.Score();
        Sheet sheet = score.getSheetPlan().getSheet();
        model.setSheetName(sheet.getName());
        model.setSheetDesc(sheet.getDescription());
        model.setPhases(sheet.getPhases().stream().map(ScoreUtil::convertPhase).collect(Collectors.toList()));
        return model;
    }

    private static gov.jiusan.star.score.model.Score.Phase convertPhase(Phase phase) {
        gov.jiusan.star.score.model.Score.Phase model = new gov.jiusan.star.score.model.Score.Phase();
        model.setName(phase.getName());
        model.setMaxScore(phase.getMaxScore());
        model.setDetails(phase.getDetails().stream().map(ScoreUtil::convertDetails).collect(Collectors.toList()));
        return model;
    }

    private static gov.jiusan.star.score.model.Score.Details convertDetails(Details details) {
        gov.jiusan.star.score.model.Score.Details model = new gov.jiusan.star.score.model.Score.Details();
        model.setDescription(details.getDescription());
        model.setEachScore(details.getEachScore());
        model.setMaxScore(details.getMaxScore());
        return model;
    }
}
