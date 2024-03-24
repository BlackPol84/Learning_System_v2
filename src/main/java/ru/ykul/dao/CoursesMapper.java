package ru.ykul.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.ykul.model.Course;
import ru.ykul.model.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoursesMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

        Course course = new Course();
        course.setId(rs.getInt("id"));
        course.setTitle(rs.getString("title"));
        course.setDescription(rs.getString("description"));

        Teacher teacher = new Teacher();
        teacher.setId(rs.getInt("teacher_id"));
        teacher.setFirstName(rs.getString("firstname"));
        teacher.setLastName(rs.getString("lastname"));

        course.setTeacher(teacher);

        return course;
    }
}
