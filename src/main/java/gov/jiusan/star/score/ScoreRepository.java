package gov.jiusan.star.score;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Marcus Lin
 */
@Repository
interface ScoreRepository extends CrudRepository<Score, Long>, ScoreRepositoryCustom {
}
