package ru.ykul.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ykul.dao.GroupsDAO;
import ru.ykul.model.Course;
import ru.ykul.model.Group;
import ru.ykul.service.GroupsService;

@Controller
@RequestMapping("/groups")
public class GroupsController {

    private final GroupsDAO groupsDAO;
    private final GroupsService groupsService;

    @Autowired
    public GroupsController(GroupsDAO groupsDAO, GroupsService groupsService) {
        this.groupsDAO = groupsDAO;
        this.groupsService = groupsService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("groupModel", groupsDAO.index());
        return "templates/groups";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("group", groupsDAO.show(id));
        return "templates/groups-edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("group") Group group, @PathVariable("id") int id) {
        groupsDAO.update(id, group);
        return "redirect:/groups";
    }

    @GetMapping("/new")
    public String newGroup(@ModelAttribute("group") Group group) {
        return "templates/groups-create";
    }

    @PostMapping()
    public String create(@ModelAttribute("group") @Valid Group group,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "templates/groups-create";

        groupsService.createGroup(group, group.getCourse().getTitle());
        return "redirect:/groups";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        groupsService.deleteGroup(id);
        return "redirect:/groups";
    }
}

