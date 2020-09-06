package by.vit.myblog.controller;

import by.vit.myblog.entity.Post;
import by.vit.myblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Post functional controller.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    /** Post service. */
    private final PostService postService;

    /**
     * Returns posts page by page.
     *
     * @param page - page number.
     * @param size - page size.
     * @return page of posts or empty page.
     */
    @GetMapping
    public Page<Post> posts(@RequestParam final Optional<Integer> page, @RequestParam final Optional<Integer> size) {
        return postService.getPaginated(PageRequest.of(page.orElse(0), size.orElse(9)));
    }

    /**
     * Returns post by id.
     *
     * @param id - post id.
     * @return post or null.
     */
    @GetMapping("/{id}")
    public Post posts(@PathVariable final Long id) {
        return postService.getById(id);
    }

    /**
     * Saves post.
     *
     * @param post - post to save.
     * @return saved post identifier.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long posts(@RequestBody @Valid final Post post) {
        return postService.save(SecurityContextHolder.getContext().getAuthentication().getName(), post);
    }

}
