package ru.ykul.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentsController {
    @GetMapping("/students")
    public String courses() {
        return "templates/students";
    }
}
