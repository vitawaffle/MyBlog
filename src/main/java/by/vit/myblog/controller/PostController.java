package by.vit.myblog.controller;

import by.vit.myblog.entity.Post;
import by.vit.myblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
