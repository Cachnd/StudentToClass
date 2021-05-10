package com.stc.demo.services;

import com.stc.demo.entities.*;
import com.stc.demo.entities.Class;
import com.stc.demo.repositories.ClassRepository;
import com.stc.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public ClassDTO getClassDTOById(Integer classId) {
        Optional<Class> classroom = classRepository.findById(classId);
        if (!classroom.isPresent()){
            return null;
        }
        return new ClassDTO(classroom.get());
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

    public StudentDTO[] getAllStudents(Integer classCode){
        Class classroom = getClassById(classCode);
        Set<Student> set = classroom.getCoursing();
        ArrayList<StudentDTO> list = new ArrayList<>();
        for (Student student: set){
            list.add(new StudentDTO(student));
        }
        StudentDTO[] result = new StudentDTO[list.size()];
        list.toArray(result);
        return result;
    }

    public ClassDTO[] getAll(){
        List<Class> list = classRepository.findAll();
        ArrayList<ClassDTO> result = new ArrayList<>();
        for(Class classroom: list){
            result.add(new ClassDTO(classroom));
        }
        ClassDTO[] array = new ClassDTO[list.size()];
        result.toArray(array);
        return array;
    }
}
