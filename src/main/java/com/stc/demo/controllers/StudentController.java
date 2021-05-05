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

    @PutMapping(path = "/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateStudent(@RequestBody Student student) {
        studentRepository.save(student);
    }

    @DeleteMapping(value = "/delete/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Integer student_id) {
        studentRepository.deleteById(student_id);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable < Student > getAllUsers() {
        return studentRepository.findAll();
    }



}
