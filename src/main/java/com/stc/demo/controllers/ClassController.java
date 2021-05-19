package com.stc.demo.controllers;

import com.stc.demo.entities.*;
import com.stc.demo.entities.Class;
import com.stc.demo.entities.DTO.ClassDTO;
import com.stc.demo.entities.DTO.StudentClassDTO;
import com.stc.demo.entities.DTO.StudentDTO;
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
    @PostMapping(path = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewClass(@RequestBody Class classroom) {
        classRepository.save(classroom);
    }

    @ApiOperation(value="View data from a single Class", response = Student.class)
    @GetMapping(path = "/{classId}")
    public @ResponseBody
    ClassDTO getClassById(@PathVariable Integer classId) {
        return classService.getClassDTOById(classId);
    }

    @ApiOperation(value="Update a Class data", response = Class.class)
    @PutMapping(path = "/{classId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateClass(@RequestBody ClassDTO classroom, @PathVariable Integer classId) {
        classService.updateClass(classroom, classId);
    }

    @ApiOperation(value="Deletes a Class", response = Class.class)
    @DeleteMapping(value = "/{classCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClass(@PathVariable Integer classCode) {
        classRepository.deleteById(classCode);
    }

    @ApiOperation(value="View a list with all Classes", response = Class.class)
    @GetMapping(path = "/all")
    public @ResponseBody ClassDTO[] getAllClasses() {
        return classService.getAll();
    }

    @ApiOperation(value="Assign a Student to a Class", response = Class.class)
        @PostMapping(path = "/assign")
    @ResponseStatus(HttpStatus.CREATED)
    public void assignStudentToClass(@RequestBody StudentClassDTO studentClassDTO){
        classService.assignStudentToClass(studentClassDTO);
    }

    @ApiOperation(value="View all Students in a Class", response = Class.class)
    @GetMapping(path = "/{classCode}/students")
    public @ResponseBody StudentDTO[] getStudents(@PathVariable Integer classCode) {
        return classService.getAllStudents(classCode);
    }
}
