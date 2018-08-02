package gov.jiusan.star.sheet;

import org.junit.Test;

import static org.junit.Assert.*;

public class SheetUtilTest {

    @Test
    public void convertToModel() {
        Sheet entity = new Sheet();
        entity.setName("星级组织评分表");
        entity.setDescription("请各位认真填写评分表，切勿乱填!");
        entity.setMaxScore(100);
        gov.jiusan.star.sheet.model.Sheet model = SheetUtil.convert(entity);
        assertEquals("星级组织评分表", model.getName());
        assertNotEquals("Fuck The Jiusan Society", model.getDescription());
        assertTrue(model.getPhases().isEmpty());
    }

    @Test
    public void convertToEntity() {
        gov.jiusan.star.sheet.model.Sheet model = new gov.jiusan.star.sheet.model.Sheet();
        model.setName("星级组织评分表");
        model.setDescription("请各位认真填写评分表，切勿乱填!");
        Sheet entity = SheetUtil.convert(model);
        assertEquals("星级组织评分表", entity.getName());
        assertNotEquals("Fuck The Jiusan Society", entity.getDescription());
        assertNull(entity.getCreateTime());
        assertNull(entity.getLastUpdateTime());
        assertNull(entity.getSeq());
        assertTrue(entity.getPhases().isEmpty());
    }
}