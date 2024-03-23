package ru.ykul.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Schedule {

    private int id;
    private Group group;
    private Teacher teacher;
    private Course course;
    @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm")
    private LocalDateTime startDate;
    @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm")
    private LocalDateTime endDate;

    public Schedule(Group group, Teacher teacher, Course course, LocalDateTime startDate, LocalDateTime endDate) {
        this.group = group;
        this.teacher = teacher;
        this.course = course;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
