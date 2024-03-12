package ru.ykul.model;

public class Group {

    private String name;
    private String course;

    public Group(String name, String course) {
        this.name = name;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Group name: " + name +
                ", course: " + course;
    }
}
