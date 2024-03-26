package ru.ykul.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ykul.dao.CoursesDao;
import ru.ykul.model.Course;
import ru.ykul.service.CoursesService;

@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CoursesController {

    private final CoursesDao coursesDao;
    private final CoursesService coursesService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("courseModel", coursesDao.index());
        return "templates/courses";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("course", coursesDao.getById(id));
        return "templates/courses-edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("courses") Course course, @PathVariable("id") int id) {
        coursesDao.update(id, course);
        return "redirect:/courses";
    }

    @GetMapping("/new")
    public String newCourse(@ModelAttribute("course") Course course) {
        return "templates/courses-create";
    }

    @PostMapping()
    public String create(@ModelAttribute("course") @Valid Course course,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "templates/courses-create";

        coursesService.createCourse(course, course.getTeacher().getFirstName(),
                course.getTeacher().getLastName());
        return "redirect:/courses";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        coursesService.deleteCourse(id);
        return "redirect:/courses";
    }
}
