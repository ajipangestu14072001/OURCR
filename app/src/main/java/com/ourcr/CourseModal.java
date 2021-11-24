package com.ourcr;

public class CourseModal {

    private String courseName;
    private int id;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CourseModal(String courseName) {
        this.courseName = courseName;
    }
}