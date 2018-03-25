package gov.jiusan.star.sheet;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Marcus Lin
 */
interface RatingSheetRepository extends CrudRepository<RatingSheet, Long>, RatingSheetRepositoryCustom {
}
