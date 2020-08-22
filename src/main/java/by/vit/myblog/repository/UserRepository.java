package by.vit.myblog.repository;

import by.vit.myblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User repository interface.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * This method finds user by username.
     *
     * @param username - user's username.
     * @return user or null.
     */
    User findByUsername(String username);

}
