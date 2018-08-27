package gov.jiusan.star.sheet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Marcus Lin
 */
@Repository
interface SheetRepository extends JpaRepository<Sheet, Long> {
}
