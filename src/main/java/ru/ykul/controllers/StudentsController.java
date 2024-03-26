package ru.ykul.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ykul.dao.StudentsDAO;
import ru.ykul.model.Student;
import ru.ykul.service.StudentsService;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentsController {

    private final StudentsDAO studentsDAO;
    private final StudentsService studentsService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("studentModel", studentsDAO.index());
        return "templates/students";
    }

    @GetMapping("/{id}/edit")
    public String show(Model model, @PathVariable("id") int id) {
        model.addAttribute("student", studentsDAO.getById(id));
        return "templates/students-edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("student") Student student, @PathVariable("id") int id) {
        studentsDAO.update(id, student);
        return "redirect:/students";
    }

    @GetMapping("/new")
    public String newStudent(@ModelAttribute("student") Student student) {
        return "templates/students-create";
    }

    @PostMapping()
    public String create(@ModelAttribute("student") @Valid Student student,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "templates/students-create";

        studentsService.createStudent(student, student.getGroup().getName());
        return "redirect:/students";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        studentsDAO.delete(id);
        return "redirect:/students";
    }
}
