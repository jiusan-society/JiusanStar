package gov.jiusan.star.sheet;

import gov.jiusan.star.sheet.model.SheetDTO;

import java.util.stream.Collectors;

/**
 * @author Marcus Lin
 */
class SheetUtil {

    static SheetDTO convert(Sheet entity) {
        SheetDTO model = new SheetDTO();
        model.setSeq(entity.getSeq());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        model.setMaxScore(entity.getMaxScore());
        model.setPhaseDTOs(entity.getPhases().stream().map(SheetUtil::convertRatingPhase).collect(Collectors.toList()));
        model.setCreateTime(entity.getCreateTime());
        model.setLastUpdateTime(entity.getLastUpdateTime());
        return model;
    }

    static Sheet convert(SheetDTO model) {
        Sheet entity = new Sheet();
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        // 目前均为百分制
        entity.setMaxScore(100);
        entity.setPhases(model.getPhaseDTOs().stream().map(SheetUtil::convertRatingPhase).collect(Collectors.toList()));
        return entity;
    }

    static Phase convertRatingPhase(SheetDTO.PhaseDTO model) {
        Phase phase = new Phase();
        phase.setName(model.getName());
        phase.setMaxScore(model.getMaxScore());
        phase.setDetails(model.getDetailsDTOs().stream().map(SheetUtil::convertRatingDetails).collect(Collectors.toList()));
        return phase;
    }

    private static SheetDTO.PhaseDTO convertRatingPhase(Phase phase) {
        SheetDTO.PhaseDTO model = new SheetDTO.PhaseDTO();
        model.setName(phase.getName());
        model.setMaxScore(phase.getMaxScore());
        model.setDetailsDTOs(phase.getDetails().stream().map(SheetUtil::convertRatingDetails).collect(Collectors.toList()));
        return model;
    }

    private static Details convertRatingDetails(SheetDTO.DetailsDTO model) {
        Details details = new Details();
        details.setDescription(model.getDescription());
        details.setEachScore(model.getEachScore());
        details.setMaxScore(model.getMaxScore());
        return details;
    }

    private static SheetDTO.DetailsDTO convertRatingDetails(Details details) {
        SheetDTO.DetailsDTO model = new SheetDTO.DetailsDTO();
        model.setDescription(details.getDescription());
        model.setEachScore(details.getEachScore());
        model.setMaxScore(details.getMaxScore());
        return model;
    }
}
