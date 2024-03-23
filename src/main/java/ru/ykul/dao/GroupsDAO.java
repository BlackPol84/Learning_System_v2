package ru.ykul.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ykul.model.Course;
import ru.ykul.model.Group;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GroupsDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<Group> index() {
        return jdbcTemplate.query("SELECT groups.*, courses.title " +
                "FROM groups LEFT JOIN " +
                "courses ON groups.course_id = courses.id", new GroupsMapper());
    }

    public Group show(int id) {
        return jdbcTemplate.query("SELECT groups.*, courses.title " +
                                "FROM groups LEFT JOIN " +
                                "courses ON groups.course_id = courses.id " +
                                "WHERE groups.id = ?",
                        new Object[]{id}, new GroupsMapper()).
                stream().findAny().orElse(null);
    }

    public Integer showGroupId(String name) {
        Integer groupId = jdbcTemplate.queryForObject("SELECT id FROM groups " +
                "WHERE name = ?", Integer.class, name);

        if (groupId != null) {
            return groupId;
        } else {
            throw new RuntimeException("The group was not found");
        }
    }

    public void create(Group group, int courseId) {
        jdbcTemplate.update("INSERT INTO groups (name, course_id) VALUES (?, ?)",
                group.getName(), courseId);
    }

    public void update(int id, Group updateGroup) {
        jdbcTemplate.update("UPDATE groups SET name = ?, course_id = " +
                        "(SELECT id FROM courses WHERE title = ?) " +
                        "WHERE id = ?", updateGroup.getName(), updateGroup.getCourse().getTitle(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM groups WHERE id = ?", id);
    }

    public void deleteCourseId(int id) {
        jdbcTemplate.update("UPDATE groups SET course_id = null WHERE course_id = ?", id);
    }
}
