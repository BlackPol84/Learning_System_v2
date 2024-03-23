package ru.ykul.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ykul.model.Schedule;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduleDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<Schedule> index() {
        return  jdbcTemplate.query("SELECT schedule.*, groups.name, teachers.firstname, " +
                "teachers.lastname, courses.title " +
                "FROM schedule " +
                "LEFT JOIN groups ON schedule.group_id = groups.id " +
                "LEFT JOIN teachers ON schedule.teacher_id = teachers.id " +
                "LEFT JOIN courses ON schedule.course_id = courses.id", new ScheduleMapper());
    }

    public Schedule show(int id) {
        return jdbcTemplate.query("SELECT schedule.*, groups.name, teachers.firstname, " +
                                "teachers.lastname, courses.title " +
                                "FROM schedule " +
                                "LEFT JOIN groups ON schedule.group_id = groups.id " +
                                "LEFT JOIN teachers ON schedule.teacher_id = teachers.id " +
                                "LEFT JOIN courses ON schedule.course_id = courses.id " +
                                "WHERE schedule.id = ?",
                        new Object[]{id}, new ScheduleMapper()).
                stream().findAny().orElse(null);
    }

    public void update(int id, Schedule updateSchedule) {
        jdbcTemplate.update("UPDATE schedule SET group_id = (SELECT id FROM groups WHERE name = ?), " +
                        "teacher_id = (SELECT id FROM teachers WHERE firstname = ? AND lastname = ?), " +
                        "course_id = (SELECT id FROM courses WHERE title = ?), " +
                        "start_date = ?, end_date = ? " +
                        "WHERE id = ?",
                updateSchedule.getGroup().getName(), updateSchedule.getTeacher().getFirstName(),
                updateSchedule.getTeacher().getLastName(), updateSchedule.getCourse().getTitle(),
                updateSchedule.getStartDate(), updateSchedule.getEndDate(), id);
    }

    public void creat(Schedule schedule, int groupId, int teacherId, int courseId) {
        jdbcTemplate.update("INSERT INTO schedule (group_id, teacher_id, " +
                "course_id, start_date, end_date) VALUES (?, ?, ?, ?, ?)",
                groupId, teacherId, courseId, schedule.getStartDate(), schedule.getEndDate());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM schedule WHERE id = ?", id);
    }
}
