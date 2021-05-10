package com.stc.demo.services;

import com.stc.demo.entities.Class;
import com.stc.demo.entities.Student;
import com.stc.demo.entities.StudentClassDTO;
import com.stc.demo.repositories.ClassRepository;
import com.stc.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ClassService {

    @Autowired
    ClassRepository classRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    public Class getClassById(Integer classId) {
        Optional<Class> classroom = classRepository.findById(classId);
        if (!classroom.isPresent()){
            return null;
        }
        return classroom.get();
    }

    public void updateClass(Class classroom, Integer classId) {
        Class c = getClassById(classId);
        if (c != null){
            c.setTitle(classroom.getTitle());
            c.setDescription((classroom.getDescription()));
            classRepository.save(c);
        }
    }

    public void assignStudentToClass(StudentClassDTO studentClassDTO){
        Student student = studentService.getStudentById(studentClassDTO.getStudent_id());
        Class classroom =  getClassById(studentClassDTO.getClass_code());
        student.assignClass(classroom);
        classroom.addStudent(student);
        studentRepository.save(student);
    }

}
