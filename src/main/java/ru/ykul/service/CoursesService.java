package ru.ykul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.ykul.dao.CoursesDao;
import ru.ykul.dao.GroupsDAO;
import ru.ykul.dao.TeacherDao;
import ru.ykul.model.Course;

@Component
public class CoursesService {

    private final CoursesDao coursesDao;
    private final GroupsDAO groupsDAO;
    private final TeacherDao teacherDao;

    @Autowired
    public CoursesService(CoursesDao coursesDao, GroupsDAO groupsDAO, TeacherDao teacherDao) {
        this.coursesDao = coursesDao;
        this.groupsDAO = groupsDAO;
        this.teacherDao = teacherDao;
    }

    @Transactional
    public void deleteCourse(int id) {
        groupsDAO.deleteCourseId(id);
        coursesDao.delete(id);
    }

    @Transactional
    public void createCourse(Course course, String firstname, String lastname) {
        int teacherId = teacherDao.showTeacherId(firstname, lastname);
        coursesDao.create(course, teacherId);
    }
}
