package gov.jiusan.star.sheet;

/**
 * @author Marcus Lin
 */
class RatingSheetUtil {

    static gov.jiusan.star.sheet.model.RatingSheet convertToModel(RatingSheet entity) {
        gov.jiusan.star.sheet.model.RatingSheet model = new gov.jiusan.star.sheet.model.RatingSheet();
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        return model;
    }

    static RatingSheet convertToEntity(gov.jiusan.star.sheet.model.RatingSheet model) {
        RatingSheet entity = new RatingSheet();
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        // 目前均为百分制
        entity.setMaxScore(100);
        return entity;
    }
}
