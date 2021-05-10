package com.stc.demo.entities.DTO;

import com.stc.demo.entities.Student;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class StudentDTO {

    private Integer student_id;

    private String firstName;

    private String lastName;

    public StudentDTO() {
    }
    public StudentDTO(Student student) {
        this.student_id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
    }

    public Integer getId() {
        return student_id;
    }

    public String getFirstName(){ return firstName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}