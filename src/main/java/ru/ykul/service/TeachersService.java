package ru.ykul.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.ykul.dao.CoursesDao;
import ru.ykul.dao.TeacherDao;

@Component
@RequiredArgsConstructor
public class TeachersService {

    private final TeacherDao teacherDao;
    private final CoursesDao coursesDao;

    @Transactional
    public void deleteTeacher(int id) {
        coursesDao.deleteTeacherId(id);
        teacherDao.delete(id);
    }
}
