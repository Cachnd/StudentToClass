package com.stc.demo.controllers;

import com.stc.demo.entities.Student;
import com.stc.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewStudent(@RequestBody Student student) {
        studentRepository.save(student);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable < Student > getAllUsers() {
        return studentRepository.findAll();
    }

}
