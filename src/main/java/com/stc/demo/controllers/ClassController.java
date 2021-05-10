package com.stc.demo.controllers;

import com.stc.demo.entities.Class;
import com.stc.demo.entities.Student;
import com.stc.demo.entities.StudentClassDTO;
import com.stc.demo.repositories.ClassRepository;
import com.stc.demo.repositories.StudentRepository;
import com.stc.demo.services.ClassService;
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

    @Autowired
    private ClassService classService;

    @ApiOperation(value="Create a Class", response = Class.class)
    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewClass(@RequestBody Class classroom) {
        classRepository.save(classroom);
    }

    @ApiOperation(value="View data from a single Class", response = Student.class)
    @GetMapping(path = "/get/{classId}")
    public @ResponseBody Class getClassById(@PathVariable Integer classId) {
        return classService.getClassById(classId);
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
        classService.assignStudentToClass(studentClassDTO);
    }
}
