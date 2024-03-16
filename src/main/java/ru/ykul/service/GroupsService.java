package ru.ykul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.ykul.dao.CoursesDao;
import ru.ykul.dao.GroupsDAO;
import ru.ykul.dao.StudentsDAO;
import ru.ykul.dao.TeacherDao;
import ru.ykul.model.Course;
import ru.ykul.model.Group;


@Component
public class GroupsService {

    private final GroupsDAO groupsDAO;
    private final CoursesDao coursesDao;
    private final StudentsDAO studentsDAO;

    @Autowired
    public GroupsService(GroupsDAO groupsDAO, CoursesDao coursesDao, StudentsDAO studentsDAO) {
        this.groupsDAO = groupsDAO;
        this.coursesDao = coursesDao;
        this.studentsDAO = studentsDAO;
    }

    @Transactional
    public void deleteGroup(int id) {
        studentsDAO.deleteGroupId(id);
        groupsDAO.delete(id);
    }

    @Transactional
    public void createGroup(Group group, String title) {
        int courseId = coursesDao.showCourseId(title);
        groupsDAO.create(group, courseId);
    }
}

