package ru.ykul.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ykul.dao.TeacherDao;
import ru.ykul.model.Teacher;
import ru.ykul.service.TeachersService;

@Controller
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeachersController {

    private final TeacherDao teacherDao;
    private final TeachersService teachersService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("teachersModel", teacherDao.index());
        return "templates/teachers";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("teacher", teacherDao.getById(id));
        return "templates/teacher-edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("teacher") Teacher teacher, @PathVariable("id") int id) {
        teacherDao.update(id, teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/new")
    public String newTeacher(@ModelAttribute("teacher") Teacher teacher) {
        return "templates/teachers-create";
    }

    @PostMapping()
    public String create(@ModelAttribute("teacher") @Valid Teacher teacher,
                       BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "templates/teachers-create";

        teacherDao.create(teacher);
        return "redirect:/teachers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        teachersService.deleteTeacher(id);
        return "redirect:/teachers";
    }
}
