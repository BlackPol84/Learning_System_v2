package ru.ykul.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ykul.dao.mapper.StudentsMapper;
import ru.ykul.model.Student;

import java.util.List;


@Component
@RequiredArgsConstructor
public class StudentsDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<Student> index() {
        return jdbcTemplate.query("SELECT students.*, groups.name " +
                "FROM students LEFT JOIN " +
                "groups ON students.group_id = groups.id", new StudentsMapper());
    }

    public Student getById(int id) {
        return jdbcTemplate.query("SELECT students.*, groups.name " +
                                "FROM students LEFT JOIN " +
                                "groups ON students.group_id = groups.id " +
                                "WHERE students.id = ?",
                        new Object[]{id}, new StudentsMapper()).
                stream().findAny().orElse(null);
    }

    public void create(Student student, int groupId) {
        jdbcTemplate.update("INSERT INTO students (firstName, lastName, group_id) VALUES (?, ?, ?)",
                student.getFirstName(), student.getLastName(), groupId);
    }

    public void update(int id, Student updateStudent) {
        jdbcTemplate.update("UPDATE students SET firstName = ?, lastName = ?, group_id = " +
                "(SELECT id FROM groups WHERE name = ?) " +
                "WHERE id = ?", updateStudent.getFirstName(), updateStudent.getLastName(),
                updateStudent.getGroup().getName(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM students WHERE id = ?", id);
    }

    public void deleteGroupId(int id) {
        jdbcTemplate.update("UPDATE students SET group_id = null WHERE group_id = ?", id);
    }
}
