package by.vit.myblog.controller;

import by.vit.myblog.entity.Password;
import by.vit.myblog.entity.Person;
import by.vit.myblog.entity.User;
import by.vit.myblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Update personal info method.
     *
     * @param person - person entity.
     * @return updated user id.
     */
    @PostMapping("/updatePersonalInfo")
    @ResponseStatus(HttpStatus.CREATED)
    public Long updatePersonalInfo(@RequestBody final Person person) {
        return userService.updatePersonalInfo(SecurityContextHolder.getContext().getAuthentication().getName(), person);
    }

    /**
     * Get current user personal info.
     *
     * @return person entity.
     */
    @GetMapping("/personalInfo")
    public Person getPersonalInfo() {
        return userService.getPersonInfoByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
