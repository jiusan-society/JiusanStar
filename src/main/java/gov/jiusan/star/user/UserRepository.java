package gov.jiusan.star.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Marcus Lin
 */
@Repository
interface UserRepository extends JpaRepository<User, Long> {

    User findUserByAccount(String account);

}
