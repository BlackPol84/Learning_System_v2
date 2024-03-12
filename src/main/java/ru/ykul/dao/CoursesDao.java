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
        return jdbcTemplate.query("SELECT * FROM courses", new BeanPropertyRowMapper<>(Course.class));
    }


}
