package by.vit.myblog.repository;

import by.vit.myblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Post repository interface.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {}
