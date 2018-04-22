package gov.jiusan.star.org;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Calendar;

class OrgRepositoryImpl implements OrgRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long create(Org entity) {
        entity.setCreateTime(Calendar.getInstance());
        em.persist(entity);
        return entity.getSeq();
    }

    @Override
    public Long update(Org entity) {
        entity.setLastUpdateTime(Calendar.getInstance());
        em.merge(entity);
        return entity.getSeq();
    }
}
