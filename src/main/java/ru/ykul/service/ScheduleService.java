package ru.ykul.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.ykul.dao.CoursesDao;
import ru.ykul.dao.GroupsDAO;
import ru.ykul.dao.ScheduleDAO;
import ru.ykul.dao.TeacherDao;
import ru.ykul.model.Group;
import ru.ykul.model.Schedule;

@Component
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleDAO scheduleDAO;
    private final GroupsDAO groupsDAO;
    private final TeacherDao teacherDao;
    private final CoursesDao coursesDao;

    public void createSchedule(Schedule schedule, String name, String firstname,
                               String lastname, String title) {
        int groupId = groupsDAO.getId(name);
        int teacherId = teacherDao.getId(firstname, lastname);
        int courseId = coursesDao.getId(title);

        scheduleDAO.create(schedule, groupId, teacherId, courseId);
    }
}
