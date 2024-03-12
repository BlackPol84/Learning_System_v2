package ru.ykul.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ykul.model.Teacher;

import java.util.List;

@Component
public class TeacherDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TeacherDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Teacher> index() {
        return jdbcTemplate.query("SELECT * FROM teachers", new BeanPropertyRowMapper<>(Teacher.class));
    }

    public Teacher show(int id) {
        return jdbcTemplate.query("SELECT teachers.id, teachers.firstname, teachers.lastname " +
                                "FROM teachers WHERE id = ?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Teacher.class)).
                stream().findAny().orElse(null);
    }

    public void create(Teacher teacher) {
        jdbcTemplate.update("INSERT INTO teachers (firstname, lastname) VALUES (?, ?)",
                teacher.getFirstName(), teacher.getLastName());
    }

    public void update(int id, Teacher updateTeacher) {
        jdbcTemplate.update("UPDATE teachers SET firstname = ?, lastname = ? WHERE id = ?",
                updateTeacher.getFirstName(), updateTeacher.getLastName(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("UPDATE courses SET teacher_id = null WHERE teacher_id = ?", id);
        jdbcTemplate.update("DELETE FROM teachers WHERE id = ?", id);
    }
}
