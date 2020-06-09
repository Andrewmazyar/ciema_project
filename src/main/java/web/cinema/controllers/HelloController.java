package web.cinema.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello_Bohdan_How_Zhittya";
    }
}