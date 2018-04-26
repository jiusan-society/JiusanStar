package gov.jiusan.star.org;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface OrgRepository extends JpaRepository<Org, Long> {

    @Query(value = "SELECT o FROM Org o WHERE o.root IS NOT NULL")
    List<Org> selectAllNonRootOrgs();

}
