package com.stc.demo.services;

import com.stc.demo.entities.Class;
import com.stc.demo.entities.DTO.ClassDTO;
import com.stc.demo.entities.Student;
import com.stc.demo.entities.DTO.StudentDTO;
import com.stc.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public StudentDTO getStudentDTOById(Integer studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (!student.isPresent()){
            return null;
        }
        return new StudentDTO(student.get());
    }

    public Student getStudentById(Integer studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (!student.isPresent()){
            return null;
        }
        return student.get();
    }

    public StudentDTO[] getAll(){
        List<Student> list = studentRepository.findAll();
        ArrayList<StudentDTO> result = new ArrayList<>();
        for(Student student: list){
            result.add(new StudentDTO(student));
        }
        StudentDTO[] array = new StudentDTO[list.size()];
        result.toArray(array);
        return array;
    }

    public void updateStudent(Student student, Integer studentId) {
        Student s = getStudentById(studentId);
        if (s != null){
            s.setFirstName(student.getFirstName());
            s.setLastName((student.getLastName()));
            studentRepository.save(s);
        }
    }
    public ClassDTO[] getAllClasses(Integer studentId){
        Student student = getStudentById(studentId);
        Set<Class> set = student.getAssignedClasses();
        ArrayList<ClassDTO> list = new ArrayList<>();
        for (Class classroom: set){
            list.add(new ClassDTO(classroom));
        }
        ClassDTO[] result = new ClassDTO[set.size()];
        list.toArray(result);
        return result;
    }
}
