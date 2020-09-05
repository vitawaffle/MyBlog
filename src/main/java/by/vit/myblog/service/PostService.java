package by.vit.myblog.service;

import by.vit.myblog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Post service interface.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
public interface PostService extends MyService<Post, Long> {

    /**
     * This method gets posts page by page.
     *
     * @param pageable Pageable class object with defined pageSize and pageNumber fields.
     * @return page of posts or empty page.
     */
    Page<Post> getPaginated(Pageable pageable);

    /**
     * This method creates post for user with specified username.
     *
     * @param username - user's username.
     * @param post - post to create.
     * @return created post identifier.
     */
    Long create(String username, Post post);

    /**
     * This method updates post of user with specified username. Throws forbidden exception if the user is not the
     * author of the post.
     *
     * @param username - user's username.
     * @param post - post to update.
     * @return updated post identifier.
     */
    Long update(String username, Post post);

    /**
     * This method saves post for user with specified username.
     *
     * @param username - user's username.
     * @param post - post to save.
     * @return saved post identifier.
     */
    Long save(String username, Post post);

    /**
     * This method deletes post of user with specified username. Throws forbidden exception if the user if not the
     * author of the post.
     *
     * @param username - user's username.
     * @param id - identifier of post to delete.
     */
    void delete(String username, Long id);

}
