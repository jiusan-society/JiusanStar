package gov.jiusan.star.org;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Marcus Lin
 */
@Repository
interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {
}
