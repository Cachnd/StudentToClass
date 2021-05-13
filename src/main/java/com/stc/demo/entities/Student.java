package com.stc.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
@Getter @Setter @NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer student_id;

    private String firstName;

    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "AssignedTo",
            joinColumns = @JoinColumn(name="student_id"),
            inverseJoinColumns = @JoinColumn(name="class_code")
    )
    private Set<Class> assignedClasses = new HashSet<>();

    public void assignClass(Class classroom){
        assignedClasses.add((classroom));
    }

    public void removeClass(Class classroom){
        assignedClasses.remove(classroom);
    }

    public Set<Class> getAssignedClasses() {
        return assignedClasses;
    }
}