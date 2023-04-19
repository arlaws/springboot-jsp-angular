package bj.logikteck.springbootjspangular.controllers;

import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    //private Path path;
    @RequestMapping({"/index", "/"})
    public String portail(Model model, Locale locale) {

        return "index";
    }
}