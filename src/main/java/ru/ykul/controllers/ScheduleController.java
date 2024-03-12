package ru.ykul.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleController {
    @GetMapping("/schedule")
    public String courses() {
        return "templates/schedule";
    }
}