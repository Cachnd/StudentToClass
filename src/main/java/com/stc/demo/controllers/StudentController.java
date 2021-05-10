package com.stc.demo.controllers;

import com.stc.demo.entities.ClassDTO;
import com.stc.demo.entities.Student;
import com.stc.demo.entities.Class;
import com.stc.demo.entities.StudentDTO;
import com.stc.demo.repositories.StudentRepository;
import com.stc.demo.services.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @ApiOperation(value="Create a Student", response = Student.class)
    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewStudent(@RequestBody Student student) {
        studentRepository.save(student);
    }

    @ApiOperation(value="View data from a single Student", response = Student.class)
    @GetMapping(path = "/get/{studentId}")
    public @ResponseBody StudentDTO getStudentById(@PathVariable Integer studentId) {
        return studentService.getStudentDTOById(studentId);
    }

    @ApiOperation(value="Updates a Student data", response = Student.class)
    @PutMapping(path = "/update/{studentId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateStudent(@RequestBody Student student, @PathVariable Integer studentId) {
        studentService.updateStudent(student, studentId);
    }

    @ApiOperation(value="Deletes a Student", response = void.class)
    @DeleteMapping(value = "/delete/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Integer studentId) {
        studentRepository.deleteById(studentId);
    }

    @ApiOperation(value="View a list with all Students", response = Student.class)
    @GetMapping(path = "/all")
    public @ResponseBody StudentDTO[] getAllUsers() {
        return studentService.getAll();
    }

    @ApiOperation(value="View all Classes a Student is registered", response = Class.class)
    @GetMapping(path = "/{studentId}/classes")
    public @ResponseBody ClassDTO[] getClasses(@PathVariable Integer studentId) {
        return studentService.getAllClasses(studentId);
    }

}
