package gov.jiusan.star.user;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Marcus Lin
 */
interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {
}
