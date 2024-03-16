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
public class Group {

    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 5, max = 20, message = "Name should be between 2 and 30 characters")
    private String name;
    private Course course;

    public Group(String name, Course course) {
        this.name = name;
        this.course = course;
    }
}
