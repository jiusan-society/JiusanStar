package gov.jiusan.star.score;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * @author Marcus Lin
 */
@Repository
interface ScoreRepository extends CrudRepository<Score, Long> {
}
