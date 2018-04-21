package gov.jiusan.star.sheet;

import java.util.stream.Collectors;

/**
 * @author Marcus Lin
 */
class RatingSheetUtil {

    static gov.jiusan.star.sheet.model.RatingSheet convert(RatingSheet entity) {
        gov.jiusan.star.sheet.model.RatingSheet model = new gov.jiusan.star.sheet.model.RatingSheet();
        model.setSeq(entity.getSeq());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        model.setRatingPhases(entity.getRatingPhases().stream().map(RatingSheetUtil::convertRatingPhase).collect(Collectors.toList()));
        model.setCreateTime(entity.getCreateTime());
        model.setLastUpdateTime(entity.getLastUpdateTime());
        return model;
    }

    static RatingSheet convert(gov.jiusan.star.sheet.model.RatingSheet model) {
        RatingSheet entity = new RatingSheet();
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        // 目前均为百分制
        entity.setMaxScore(100);
        entity.setRatingPhases(model.getRatingPhases().stream().map(RatingSheetUtil::convertRatingPhase).collect(Collectors.toList()));
        return entity;
    }

    private static RatingPhase convertRatingPhase(gov.jiusan.star.sheet.model.RatingPhase model) {
        RatingPhase ratingPhase = new RatingPhase();
        ratingPhase.setName(model.getName());
        ratingPhase.setMaxScore(model.getMaxScore());
        ratingPhase.setRatingDetails(model.getRatingDetails().stream().map(RatingSheetUtil::convertRatingDetails).collect(Collectors.toList()));
        return ratingPhase;
    }

    private static gov.jiusan.star.sheet.model.RatingPhase convertRatingPhase(RatingPhase phase) {
        gov.jiusan.star.sheet.model.RatingPhase model = new gov.jiusan.star.sheet.model.RatingPhase();
        model.setName(phase.getName());
        model.setMaxScore(phase.getMaxScore());
        model.setRatingDetails(phase.getRatingDetails().stream().map(RatingSheetUtil::convertRatingDetails).collect(Collectors.toList()));
        return model;
    }

    private static RatingDetails convertRatingDetails(gov.jiusan.star.sheet.model.RatingDetails model) {
        RatingDetails details = new RatingDetails();
        details.setDescription(model.getDescription());
        details.setEachScore(model.getEachScore());
        details.setMaxScore(model.getMaxScore());
        return details;
    }

    private static gov.jiusan.star.sheet.model.RatingDetails convertRatingDetails(RatingDetails details) {
        gov.jiusan.star.sheet.model.RatingDetails model = new gov.jiusan.star.sheet.model.RatingDetails();
        model.setDescription(details.getDescription());
        model.setEachScore(details.getEachScore());
        model.setMaxScore(details.getMaxScore());
        return model;
    }
}
