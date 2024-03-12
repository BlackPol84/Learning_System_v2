package ru.ykul.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GroupsController {
    @GetMapping("/groups")
    public String courses() {
        return "templates/groups";
    }
}

