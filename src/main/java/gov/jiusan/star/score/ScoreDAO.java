package gov.jiusan.star.score;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.Optional;

/**
 * @author Marcus Lin
 */
@ApplicationScoped
class ScoreDAO {

    @PersistenceContext
    private EntityManager em;

    void create(Score score) {
        Calendar calendar = Calendar.getInstance();
        score.setCreateTime(calendar);
        score.setLastUpdateTime(calendar);
        em.persist(score);
    }

    Optional<Score> retrieve(Long id) {
        Score score = em.find(Score.class, id);
        return Optional.ofNullable(score);
    }

    Score update(Score score) {
        score.setLastUpdateTime(Calendar.getInstance());
        return em.merge(score);
    }

    void delete(Score score) {
        em.remove(score);
    }
}
