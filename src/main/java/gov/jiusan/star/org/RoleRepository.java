package gov.jiusan.star.org;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Marcus Lin
 */
@Repository
interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByName(String name);

}
