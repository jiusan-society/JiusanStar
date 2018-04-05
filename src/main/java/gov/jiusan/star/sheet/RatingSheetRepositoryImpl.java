package gov.jiusan.star.sheet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Calendar;

/**
 * @author Marcus Lin
 */
@Transactional
class RatingSheetRepositoryImpl implements RatingSheetRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long create(RatingSheet entity) {
        entity.setCreateTime(Calendar.getInstance());
        entity.setLastUpdateTime(Calendar.getInstance());
        em.persist(entity);
        return entity.getSeq();
    }

    @Override
    public Long update(RatingSheet entity) {
        entity.setLastUpdateTime(Calendar.getInstance());
        em.merge(entity);
        return entity.getSeq();
    }
}
