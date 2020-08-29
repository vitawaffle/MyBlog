package by.vit.myblog.controller;

import by.vit.myblog.entity.Password;
import by.vit.myblog.entity.User;
import by.vit.myblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Long signIn(@RequestBody final User user) {
        return userService.signIn(user);
    }

    /**
     * Update password method.
     *
     * @param password - password entity.
     * @return updated user id.
     */
    @PostMapping("/updatePassword")
    @ResponseStatus(HttpStatus.CREATED)
    public Long updatePassword(@RequestBody final Password password) {
        return userService.updatePassword(SecurityContextHolder.getContext().getAuthentication().getName(), password);
    }

}
