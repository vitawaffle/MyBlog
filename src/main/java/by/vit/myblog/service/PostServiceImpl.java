package by.vit.myblog.service;

import by.vit.myblog.comparator.PostDateComparator;
import by.vit.myblog.entity.Post;
import by.vit.myblog.exception.ForbiddenException;
import by.vit.myblog.repository.PostRepository;
import by.vit.myblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Post service.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    /** Post repository. */
    private final PostRepository postRepository;

    /** User repository. */
    private final UserRepository userRepository;

    /** Collection comparator. */
    private final Comparator<Post> comparator = new PostDateComparator();

    @Override
    public Page<Post> getPaginated(final Pageable pageable) {
        val posts = postRepository.findAll();
        posts.sort(comparator);
        val startItem = pageable.getPageNumber() * pageable.getPageSize();
        var page = Collections.<Post>emptyList();
        if (startItem >= 0 && startItem < posts.size())
            page = posts.subList(startItem, Math.min(startItem + pageable.getPageSize(), posts.size()));
        return new PageImpl<>(page, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), posts.size());
    }

    @Override
    public Long create(final String username, final Post post) {
        val user = userRepository.findByUsername(username);
        post.setId(null);
        post.setCreation(new Date(System.currentTimeMillis()));
        post.setUser(user);
        return save(post);
    }

    @Override
    public Long update(final String username, final Post post) {
        Post dbPost = null;
        if (post.getId() != null)
            dbPost = postRepository.findById(post.getId()).orElse(null);
        if (dbPost == null)
            return create(username, post);
        if (!dbPost.getUser().getUsername().equals(username))
            throw new ForbiddenException();
        dbPost.setTitle(post.getTitle());
        dbPost.setContent(post.getContent());
        return save(dbPost);
    }

    @Override
    public void delete(final String username, final Long id) {
        val post = postRepository.findById(id).orElse(null);
        if (post != null) {
            if (!post.getUser().getUsername().equals(username))
                throw new ForbiddenException();
            postRepository.delete(post);
        }
    }

    @Override
    public Post getById(final Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Long save(final Post post) {
        return postRepository.save(post).getId();
    }

    @Override
    public void deleteById(final Long id) {
        try {
            postRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ignore) {}
    }

}
