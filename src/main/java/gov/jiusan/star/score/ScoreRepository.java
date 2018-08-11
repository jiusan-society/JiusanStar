package gov.jiusan.star.score;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Marcus Lin
 */
@Repository
interface ScoreRepository extends JpaRepository<Score, Long> {
}
