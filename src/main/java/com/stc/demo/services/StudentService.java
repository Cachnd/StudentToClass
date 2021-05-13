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

    /**
     * Given a {@code studentId} tries to return the corresponding Student
     * If the {@code studentId} doesn't exists returns {@code null}.
     *
     * @param studentId The {@code studentId} of the Student.
     * @return {@link StudentDTO} if the {@code studentId} exists; {@code null}
     * otherwise.
     */
    public StudentDTO getStudentDTOById(Integer studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (!student.isPresent()){
            return null;
        }
        return new StudentDTO(student.get());
    }

    /**
     * Given a {@code studentId} tries to return the corresponding Student
     * If the {@code studentId} doesn't exists returns {@code null}.
     *
     * @param studentId The {@code studentId} of the Student.
     * @return {@link Student} if the {@code studentId} exists; {@code null}
     * otherwise.
     */
    public Student getStudentById(Integer studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (!student.isPresent()){
            return null;
        }
        return student.get();
    }

    /**
     * Returns an <code>Array</code> of {@link StudentDTO} containing all
     * Students.
     *
     * @return {@code StudentDTO[]}, with all known Students.
     */
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

    /**
     * Given a {@link StudentDTO} and a {@code StudentId} tries to update
     * the Student with {@code StudentId} with the new values in case the
     * {@code StudentId} doesn't exist it will do nothing. If the values
     * of {@code StudentDTO} are not set the missing values will be set
     * to {@code null}.
     *
     * @param student The {@link StudentDTO} with the new values.
     * @param studentId The {@code studentId} of the Student to be updated.
     */
    public void updateStudent(StudentDTO student, Integer studentId) {
        Student s = getStudentById(studentId);
        if (s != null){
            s.setFirstName(student.getFirstName());
            s.setLastName((student.getLastName()));
            studentRepository.save(s);
        }
    }

    /**
     * Given a {@code studentId} returns an <code>Array</code> of
     * {@link ClassDTO} containing all Classes the Student is
     * assigned to.
     *
     * @return {@code ClassDTO[]}, with all Classes the Student
     * is assigned to.
     */
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
