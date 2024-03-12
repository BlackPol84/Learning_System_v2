package ru.ykul.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoursesController {

    @GetMapping("/courses")
    public String courses() {
        return "templates/courses";
    }
}
