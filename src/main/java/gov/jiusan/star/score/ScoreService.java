package gov.jiusan.star.score;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Marcus Lin
 */
@Stateless
public class ScoreService {

    @PersistenceContext
    private EntityManager em;

    // TODO[2017-08-19][Marcus Lin]: Need to change the return type to ScoreCreateResponse
    public void createScore(ScoreCreateRequest scoreCreateRequest) {
        Score score = ScoreUtil.toEntity(scoreCreateRequest);
        em.persist(score);
    }
}
