package com.stc.demo.services;

import com.stc.demo.entities.*;
import com.stc.demo.entities.Class;
import com.stc.demo.entities.DTO.ClassDTO;
import com.stc.demo.entities.DTO.StudentClassDTO;
import com.stc.demo.entities.DTO.StudentDTO;
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

    /**
     * Given a {@code classId} tries to return the corresponding Class
     * If the {@code classId} doesn't exists returns {@code null}.
     *
     * @param classId The Code of the Class.
     * @return {@link Class} if the {@code classId} exists; {@code null}
     * otherwise.
     */
    public Class getClassById(Integer classId) {
        Optional<Class> classroom = classRepository.findById(classId);
        if (!classroom.isPresent()){
            return null;
        }
        return classroom.get();
    }

    /**
     * Given a {@code classId} tries to return the corresponding Class
     * If the {@code classId} doesn't exists returns {@code null}.
     *
     * @param classId The {@code classId} of the Class.
     * @return {@link Class} if the {@code classId} exists; {@code null}
     * otherwise.
     */
    public ClassDTO getClassDTOById(Integer classId) {
        Optional<Class> classroom = classRepository.findById(classId);
        if (!classroom.isPresent()){
            return null;
        }
        return new ClassDTO(classroom.get());
    }

    /**
     * Given a {@link ClassDTO} and a {@code classId} tries to update
     * the Class with {@code classId} with the new values in case the
     * {@code classId} doesn't exist it will do nothing. If the values
     * of {@code classDTO} are not set the missing values will be set
     * to {@code null}.
     *
     * @param classroom The {@link ClassDTO} with the new values.
     * @param classId The Code of the Class to be updated.
     */
    public void updateClass(ClassDTO classroom, Integer classId) {
        Class c = getClassById(classId);
        if (c != null){
            c.setTitle(classroom.getTitle());
            c.setDescription((classroom.getDescription()));
            classRepository.save(c);
        }
    }

    /**
     * Given a {@link StudentClassDTO} tries to assign the corresponding
     * Student to the corresponding Class and the other way around.
     * In case the Class or the Student doesn't exist it will throw an
     * {@code NullPointerException}
     *
     * @param studentClassDTO A {@link StudentClassDTO} Containing a
     * {@code studentId} and a {@code classId}
     */
    public void assignStudentToClass(StudentClassDTO studentClassDTO){
        Student student = studentService.getStudentById(studentClassDTO.getStudent_id());
        Class classroom =  getClassById(studentClassDTO.getClass_code());
        if (!student.getAssignedClasses().contains(classroom)){
            student.assignClass(classroom);
            classroom.addStudent(student);
            studentRepository.save(student);
        }
    }

    /**
     * Given a {@code classId} returns an <code>Array</code> of
     * {@link StudentDTO} containing all Students that have been
     * assigned to the Class.
     *
     * @return {@code StudentDTO[]}, with all Students assigned
     * to the Class.
     */
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

    /**
     * Returns an <code>Array</code> of {@link ClassDTO} containing all
     * Classes.
     *
     * @return {@code ClassDTO[]}, with all known Classes.
     */
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
