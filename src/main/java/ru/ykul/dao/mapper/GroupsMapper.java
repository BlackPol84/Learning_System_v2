package ru.ykul.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ykul.model.Course;
import ru.ykul.model.Group;
import ru.ykul.model.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupsMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
        Group group = new Group();
        group.setId(rs.getInt("id"));
        group.setName(rs.getString("name"));

        Course course = new Course();
        course.setId(rs.getInt("course_id"));
        course.setTitle(rs.getString("title"));

        group.setCourse(course);

        return group;
    }
}
