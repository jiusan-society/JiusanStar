package gov.jiusan.star.sheet;

import java.util.stream.Collectors;

/**
 * @author Marcus Lin
 */
class SheetUtil {

    static gov.jiusan.star.sheet.model.Sheet convert(Sheet entity) {
        gov.jiusan.star.sheet.model.Sheet model = new gov.jiusan.star.sheet.model.Sheet();
        model.setSeq(entity.getSeq());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        model.setMaxScore(entity.getMaxScore());
        model.setPhases(entity.getPhases().stream().map(SheetUtil::convertRatingPhase).collect(Collectors.toList()));
        model.setCreateTime(entity.getCreateTime());
        model.setLastUpdateTime(entity.getLastUpdateTime());
        return model;
    }

    static Sheet convert(gov.jiusan.star.sheet.model.Sheet model) {
        Sheet entity = new Sheet();
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        // 目前均为百分制
        entity.setMaxScore(100);
        entity.setPhases(model.getPhases().stream().map(SheetUtil::convertRatingPhase).collect(Collectors.toList()));
        return entity;
    }

    static Phase convertRatingPhase(gov.jiusan.star.sheet.model.Sheet.Phase model) {
        Phase phase = new Phase();
        phase.setName(model.getName());
        phase.setMaxScore(model.getMaxScore());
        phase.setDetails(model.getDetails().stream().map(SheetUtil::convertRatingDetails).collect(Collectors.toList()));
        return phase;
    }

    private static gov.jiusan.star.sheet.model.Sheet.Phase convertRatingPhase(Phase phase) {
        gov.jiusan.star.sheet.model.Sheet.Phase model = new gov.jiusan.star.sheet.model.Sheet.Phase();
        model.setName(phase.getName());
        model.setMaxScore(phase.getMaxScore());
        model.setDetails(phase.getDetails().stream().map(SheetUtil::convertRatingDetails).collect(Collectors.toList()));
        return model;
    }

    private static Details convertRatingDetails(gov.jiusan.star.sheet.model.Sheet.Details model) {
        Details details = new Details();
        details.setDescription(model.getDescription());
        details.setEachScore(model.getEachScore());
        details.setMaxScore(model.getMaxScore());
        return details;
    }

    private static gov.jiusan.star.sheet.model.Sheet.Details convertRatingDetails(Details details) {
        gov.jiusan.star.sheet.model.Sheet.Details model = new gov.jiusan.star.sheet.model.Sheet.Details();
        model.setDescription(details.getDescription());
        model.setEachScore(details.getEachScore());
        model.setMaxScore(details.getMaxScore());
        return model;
    }
}
