package com.mycompany.carRental;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/index")
    public String showIndexPage() {
        System.out.println("main controller");
        return "index";
    }
    @GetMapping("/")
    public String showHomePage() {
        System.out.println("main controller");
        return "home";
    }
}
