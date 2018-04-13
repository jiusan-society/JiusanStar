package gov.jiusan.star.sheet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Marcus Lin
 */
@Repository
interface RatingSheetRepository extends CrudRepository<RatingSheet, Long>, RatingSheetRepositoryCustom {
}
