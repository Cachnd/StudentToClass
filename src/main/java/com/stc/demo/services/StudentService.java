package com.stc.demo.services;

import com.stc.demo.entities.Student;
import com.stc.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student getStudentById(Integer studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (!student.isPresent()){
            return null;
        }
        return student.get();
    }

    public void updateStudent(Student student, Integer studentId) {
        Student s = getStudentById(studentId);
        if (s != null){
            s.setFirstName(student.getFirstName());
            s.setLastName((student.getLastName()));
            studentRepository.save(s);
        }
    }
}
