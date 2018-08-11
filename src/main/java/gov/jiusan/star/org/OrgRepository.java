package gov.jiusan.star.org;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface OrgRepository extends JpaRepository<Org, Long> {

    List<Org> findOrgsByRootCodeIsNotNull();

    List<Org> findOrgsByParentCode(String parentCode);
}
