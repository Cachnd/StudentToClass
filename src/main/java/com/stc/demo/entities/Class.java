package com.stc.demo.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "classes")
public class Class {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer class_code;

    private String title;

    private String description;

    @ManyToMany
    Set<Student> coursing = new HashSet<>();

    public Class() {
    }

    public Integer getCode() {
        return class_code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addStudent(Student student){
        coursing.add(student);
    }

    public void removeStudent(Student student){
        coursing.remove(student);
    }

    public Set<Student> getCoursing() {
        return coursing;
    }
}