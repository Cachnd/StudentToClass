package com.stc.demo;

import com.stc.demo.entities.DTO.StudentDTO;
import com.stc.demo.entities.Student;
import com.stc.demo.repositories.ClassRepository;
import com.stc.demo.repositories.StudentRepository;
import com.stc.demo.services.StudentService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
class StudentServiceTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @Test
    public void testGetAllStudents(){
        Assert.assertTrue(studentService.getAll() instanceof StudentDTO[]);
    }

    @Test
    public void testGetStudent(){
        StudentDTO[] list = studentService.getAll();
        StudentDTO student = new StudentDTO();
        if (list.length > 0){
            student = list[0];
        }
        Student otherStudent = studentService.getStudentById(student.getId());
        Assert.assertEquals("Same firstName", student.getFirstName(), otherStudent.getFirstName());
        Assert.assertEquals("Same lastName", student.getFirstName(), otherStudent.getFirstName());
        Assert.assertEquals("Same id", student.getId(), otherStudent.getId());
    }

    @Test
    public void testAddDeleteNewUser(){
        Student student = new Student();
        student.setFirstName("TestingFirstName");
        student.setLastName("TestingLastName");
        studentRepository.save(student);
        Student other = studentService.getStudentById(student.getId());
        boolean equals = true;
        if (!student.getFirstName().equals(other.getFirstName()))
            equals = false;
        if (!student.getLastName().equals(other.getLastName()))
            equals = false;
        if (student.getId() != other.getId())
            equals = false;
        Assert.assertTrue("Test Add", equals);
        studentRepository.deleteById(student.getId());
        Assert.assertEquals("Test Delete", null, studentService.getStudentById(student.getId()));
    }

}
