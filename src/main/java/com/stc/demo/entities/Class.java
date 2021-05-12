package com.stc.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "classes")
@Getter @Setter @NoArgsConstructor
public class Class {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer class_code;

    private String title;

    private String description;

    @ManyToMany
    Set<Student> coursing = new HashSet<>();

    public void addStudent(Student student){
        coursing.add(student);
    }

    public void removeStudent(Student student){
        coursing.remove(student);
    }

}