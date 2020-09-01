package by.vit.myblog.service;

import by.vit.myblog.entity.Post;
import by.vit.myblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;

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

    @Override
    public Page<Post> getPaginated(final Pageable pageable) {
        val posts = postRepository.findAll();
        val startItem = pageable.getPageNumber() * pageable.getPageSize();
        var page = Collections.<Post>emptyList();
        if (startItem >= 0 && startItem < posts.size())
            page = posts.subList(startItem, Math.min(startItem + pageable.getPageSize(), posts.size()));
        return new PageImpl<>(page, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), posts.size());
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
