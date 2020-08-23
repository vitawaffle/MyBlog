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
     * @return string name of view.
     */
    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("title", "Home");
        return "index";
    }

}