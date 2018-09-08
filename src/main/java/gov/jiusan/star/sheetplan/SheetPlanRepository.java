package gov.jiusan.star.sheetplan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Marcus Lin
 */
@Repository
interface SheetPlanRepository extends JpaRepository<SheetPlan, Long> {
}
