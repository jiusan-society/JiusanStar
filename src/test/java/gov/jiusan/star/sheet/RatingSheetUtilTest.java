package gov.jiusan.star.sheet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RatingSheetUtilTest {

    @Test
    void convertToModel() {
        RatingSheet entity = new RatingSheet();
        entity.setName("星级组织评分表");
        entity.setDescription("请各位认真填写评分表，切勿乱填!");
        entity.setMaxScore(100);
        gov.jiusan.star.sheet.model.RatingSheet model = RatingSheetUtil.convertToModel(entity);
        assertEquals("星级组织评分表", model.getName());
        assertNotEquals("Fuck The Jiusan Society", model.getDescription());
        assertTrue(model.getRatingPhases().isEmpty());
    }

    @Test
    void convertToEntity() {
        gov.jiusan.star.sheet.model.RatingSheet model = new gov.jiusan.star.sheet.model.RatingSheet();
        model.setName("星级组织评分表");
        model.setDescription("请各位认真填写评分表，切勿乱填!");
        RatingSheet entity = RatingSheetUtil.convertToEntity(model);
        assertEquals("星级组织评分表", entity.getName());
        assertNotEquals("Fuck The Jiusan Society", entity.getDescription());
        assertNull(entity.getCreateTime());
        assertNull(entity.getLastUpdateTime());
        assertNull(entity.getSeq());
        assertTrue(entity.getRatingPhases().isEmpty());
    }

}