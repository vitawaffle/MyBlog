package by.vit.myblog.service;

import by.vit.myblog.entity.Password;
import by.vit.myblog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * User service interface.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
public interface UserService extends MyService<User, Long> {

    /**
     * This method gets users page by page.
     *
     * @param pageable - Pageable class object with defined pageSize and pageNumber fields.
     * @return page of users or empty page.
     */
    Page<User> getPaginated(Pageable pageable);

    /**
     * This method register new user.
     *
     * @param user - user to register.
     * @return id of registered user.
     */
    Long signIn(User user);

    /**
     * This method updates password for user with specified username.
     *
     * @param username - user's username.
     * @param password - password entity.
     * @return updated user id.
     */
    Long updatePassword(String username, Password password);

}
