package com.stc.demo.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer student_id;

    private String firstName;

    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "AssignedTo",
            joinColumns = @JoinColumn(name="student_id"),
            inverseJoinColumns = @JoinColumn(name="class_code")
    )
    private Set<Class> assignedClasses = new HashSet<>();

    public Student() {
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

    public void assignClass(Class classroom){
        assignedClasses.add((classroom));
    }

    public void removeClass(Class classroom){
        assignedClasses.remove(classroom);
    }
}