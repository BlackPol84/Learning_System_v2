package ru.ykul.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.ykul.dao.GroupsDAO;
import ru.ykul.dao.StudentsDAO;
import ru.ykul.model.Group;
import ru.ykul.model.Student;

@Component
@RequiredArgsConstructor
public class StudentsService {

    private final StudentsDAO studentsDAO;
    private final GroupsDAO groupsDAO;

    public void createStudent(Student student, String name) {
        int groupId = groupsDAO.getId(name);
        studentsDAO.create(student, groupId);
    }
}
