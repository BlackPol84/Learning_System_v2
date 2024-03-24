package ru.ykul.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ykul.dao.ScheduleDAO;
import ru.ykul.model.Schedule;
import ru.ykul.service.ScheduleService;

@Controller
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleDAO scheduleDAO;
    private final ScheduleService scheduleService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("scheduleModel", scheduleDAO.index());
        return "templates/schedule";
    }

    @GetMapping("/new")
    public String newSchedule(@ModelAttribute("schedule") Schedule schedule) {
        return "templates/schedule-create";
    }

    @PostMapping()
    public String create(@ModelAttribute("schedule") @Valid Schedule schedule,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "templates/schedule-create";

        scheduleService.createSchedule(schedule, schedule.getGroup().getName(), schedule.getTeacher().
                        getFirstName(), schedule.getTeacher().getLastName(), schedule.getCourse().getTitle());
        return "redirect:/schedule";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("schedule", scheduleDAO.show(id));
        return "templates/schedule-edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("schedule") Schedule schedule, @PathVariable("id") int id) {
        scheduleDAO.update(id, schedule);
        return "redirect:/schedule";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        scheduleDAO.delete(id);
        return "redirect:/schedule";
    }
}