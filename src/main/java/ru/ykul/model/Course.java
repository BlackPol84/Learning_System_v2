package ru.ykul.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Course {

    private int id;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 40, message = "Title should be between 2 and 30 characters")
    private String title;
    @NotEmpty(message = "Description should not be empty")
    @Size(min = 40, max = 100, message = "Title should be between 40 and 100 characters")
    private String description;
    private Teacher teacher;

    public Course(String title, String description, Teacher teacher) {
        this.title = title;
        this.description = description;
        this.teacher = teacher;
    }
}
