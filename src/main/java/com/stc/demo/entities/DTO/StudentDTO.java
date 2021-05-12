package com.stc.demo.entities.DTO;

import com.stc.demo.entities.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor
public class StudentDTO {

    private Integer student_id;

    private String firstName;

    private String lastName;

    public StudentDTO(Student student) {
        this.student_id = student.getStudent_id();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
    }

}