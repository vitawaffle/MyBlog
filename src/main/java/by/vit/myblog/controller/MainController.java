package by.vit.myblog.controller;

import by.vit.myblog.entity.User;
import by.vit.myblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Application main functional controller.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class MainController {

    /** User service. */
    private final UserService userService;

    /**
     * Register user method.
     *
     * @param user - user to register.
     * @return id of registered user.
     */
    @PostMapping("/signIn")
    public Long signIn(@RequestBody final User user) {
        return userService.signIn(user);
    }

}
