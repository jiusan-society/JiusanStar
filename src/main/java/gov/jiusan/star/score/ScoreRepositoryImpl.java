package gov.jiusan.star.score;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Calendar;

/**
 * @author Marcus Lin
 */
@Transactional
public class ScoreRepositoryImpl implements ScoreRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Score entity) {
        entity.setCreateTime(Calendar.getInstance());
        entity.setLastUpdateTime(Calendar.getInstance());
        em.persist(entity);
    }

    @Override
    public void update(Score entity) {
        entity.setLastUpdateTime(Calendar.getInstance());
        em.merge(entity);
    }
}
