package com.stc.demo;

import com.stc.demo.entities.Class;
import com.stc.demo.entities.DTO.ClassDTO;
import com.stc.demo.repositories.ClassRepository;
import com.stc.demo.services.ClassService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClassServiceTest {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ClassService classService;

    private Class classroom;

    @BeforeAll
    public void setup(){
        classroom = new Class();
        classroom.setTitle("TestingClass");
        classroom.setDescription("TestingDescription");
        classRepository.save(classroom);
    }

    @AfterAll
    public void teardown(){
        classRepository.deleteById(classroom.getClass_code());
    }

    @Test
    public void testClassroom(){
        Assert.assertNotNull(this.classroom.getClass_code());
    }

    @Test
    public void testGetAllClasses(){
        ClassDTO[] list = classService.getAll();
        ClassDTO other = list[list.length-1];
        Assert.assertEquals("Test latest Class in All", classroom.getClass_code(), other.getClass_code());
    }

}
