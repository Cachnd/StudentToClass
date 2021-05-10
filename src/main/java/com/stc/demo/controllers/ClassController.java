package com.stc.demo.controllers;

import com.stc.demo.entities.Class;
import com.stc.demo.entities.Student;
import com.stc.demo.entities.StudentClassDTO;
import com.stc.demo.repositories.ClassRepository;
import com.stc.demo.repositories.StudentRepository;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value="Create a Class", response = Class.class)
    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewClass(@RequestBody Class classroom) {
        classRepository.save(classroom);
    }

    @ApiOperation(value="Update a Class data", response = Class.class)
    @PutMapping(path = "/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateClass(@RequestBody Class classroom) {
        classRepository.save(classroom);
    }

    @ApiOperation(value="Deletes a Class", response = Class.class)
    @DeleteMapping(value = "/delete/{classCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClass(@PathVariable Integer class_code) {
        classRepository.deleteById(class_code);
    }

    @ApiOperation(value="View a list with all Classes", response = Class.class)
    @GetMapping(path = "/all")
    public @ResponseBody Iterable < Class > getAllClasses() {
        return classRepository.findAll();
    }

    @ApiOperation(value="Assign a Student to a Class", response = Class.class)
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
