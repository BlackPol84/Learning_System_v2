package ru.ykul.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ykul.model.Course;
import ru.ykul.model.Teacher;

import java.util.List;

@Component
public class CoursesDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CoursesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Course> index() {
        return jdbcTemplate.query("SELECT courses.*, teachers.firstname, teachers.lastname " +
                "FROM courses LEFT JOIN " +
                "teachers ON courses.teacher_id = teachers.id", new CoursesMapper());
    }

    public Course show(int id) {
        return jdbcTemplate.query("SELECT courses.*, " +
                                "teachers.firstname, teachers.lastname " +
                                "FROM courses LEFT JOIN " +
                                "teachers ON courses.teacher_id = teachers.id " +
                                "WHERE courses.id = ?",
                        new Object[]{id}, new CoursesMapper()).
                stream().findAny().orElse(null);
    }

    public Integer showCourseId(String title) {
        Integer courseId = jdbcTemplate.queryForObject("SELECT id FROM courses " +
                "WHERE title = ?", Integer.class, title);

        if (courseId != null) {
            return courseId;
        } else {
            throw new RuntimeException("The courses was not found");
        }
    }

    public void create(Course course, int teacherId) {
        jdbcTemplate.update("INSERT INTO courses (title, description, teacher_id) VALUES (?, ?, ?)",
                course.getTitle(), course.getDescription(), teacherId);
    }

    public void update(int id, Course updateCourse) {
        jdbcTemplate.update("UPDATE courses SET title = ?, description = ?, teacher_id = " +
                        "(SELECT id FROM teachers WHERE firstname = ? AND lastname = ?) " +
                        "WHERE id = ?",
                updateCourse.getTitle(), updateCourse.getDescription(),
                updateCourse.getTeacher().getFirstName(), updateCourse.getTeacher().getLastName(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM courses WHERE id = ?", id);
    }

    public void deleteTeacherId(int id) {
        jdbcTemplate.update("UPDATE courses SET teacher_id = null WHERE teacher_id = ?", id);
    }
}
