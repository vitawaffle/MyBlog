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

}
