package gov.jiusan.star.sheet;

/**
 * @author Marcus Lin
 */
interface SheetRepositoryCustom {

    Long create(Sheet entity);

    Sheet update(Sheet entity);
}
