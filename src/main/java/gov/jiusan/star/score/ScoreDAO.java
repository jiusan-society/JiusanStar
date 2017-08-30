package gov.jiusan.star.score;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

/**
 * @author Marcus Lin
 */
@ApplicationScoped
public class ScoreDAO {

    @PersistenceContext
    private EntityManager em;

    public void create(Score score) {
        em.persist(score);
    }

    public Optional<Score> retrieve(Long id) {
        Score score = em.find(Score.class, id);
        return Optional.ofNullable(score);
    }

    public Score update(Score score) {
        return em.merge(score);
    }

    public void delete(Score score) {
        em.remove(score);
    }
}
