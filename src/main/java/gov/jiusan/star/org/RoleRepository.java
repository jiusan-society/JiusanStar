package gov.jiusan.star.org;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Marcus Lin
 */
@Repository
interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT r FROM Role r WHERE r.name = :name")
    Role findRoleByName(String name);

}
