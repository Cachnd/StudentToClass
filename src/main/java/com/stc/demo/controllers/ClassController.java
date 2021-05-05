package com.stc.demo.controllers;

import com.stc.demo.entities.Class;
import com.stc.demo.entities.Student;
import com.stc.demo.entities.StudentClassDTO;
import com.stc.demo.repositories.ClassRepository;
import com.stc.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classes")
public class ClassController {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewClass(@RequestBody Class classroom) {
        classRepository.save(classroom);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable < Class > getAllClasses() {
        return classRepository.findAll();
    }

    @PostMapping(path = "/assign")
    @ResponseStatus(HttpStatus.CREATED)
    public void assignStudentToClass(@RequestBody StudentClassDTO studentClassDTO){
        Student student = studentRepository.getOne(studentClassDTO.getStudent_id());
        Class classroom =  classRepository.getOne(studentClassDTO.getClass_code());
        student.assignClass(classroom);
        classroom.addStudent(student);
        studentRepository.save(student);
    }
}
