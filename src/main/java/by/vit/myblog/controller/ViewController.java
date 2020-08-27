package by.vit.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This class controls application views.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
@Controller
public class ViewController {

    /**
     * Index view.
     *
     * @param model - view model.
     * @return string name of view.
     */
    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("title", "Home");
        return "index";
    }

    /**
     * Login view.
     *
     * @param model - view model.
     * @return string name of view.
     */
    @GetMapping("/login")
    public String login(final Model model) {
        model.addAttribute("title", "Log in");
        return "login";
    }

    /**
     * Sign in view.
     *
     * @param model - view model.
     * @return string name of view.
     */
    @GetMapping("/signIn")
    public String signIn(final Model model) {
        model.addAttribute("title", "Sign in");
        return "signIn";
    }

}
