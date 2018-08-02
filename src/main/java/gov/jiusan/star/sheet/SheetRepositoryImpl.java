package gov.jiusan.star.sheet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Calendar;

/**
 * @author Marcus Lin
 */
@Transactional
class SheetRepositoryImpl implements SheetRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long create(Sheet entity) {
        entity.setCreateTime(Calendar.getInstance());
        entity.setLastUpdateTime(Calendar.getInstance());
        em.persist(entity);
        return entity.getSeq();
    }

    @Override
    public Sheet update(Sheet entity) {
        entity.setLastUpdateTime(Calendar.getInstance());
        return em.merge(entity);
    }
}
