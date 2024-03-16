package ru.ykul.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class StudentsDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void deleteGroupId(int id) {
        jdbcTemplate.update("UPDATE students SET group_id = null WHERE group_id = ?", id);
    }
}
