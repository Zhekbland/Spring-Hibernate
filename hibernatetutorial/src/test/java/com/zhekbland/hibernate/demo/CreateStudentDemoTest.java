package com.zhekbland.hibernate.demo;

import com.zhekbland.hibernate.demo.entity.Student;
import com.zhekbland.jdbc.DataCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateStudentDemoTest {

    private static DataCreator dataCreator = new DataCreator();

    @Before
    public void init() {
        this.dataCreator.getConnection();
        this.dataCreator.checkTableExist();
    }

    @After
    public void destroy() {
        this.dataCreator.deleteTable();
    }

    @Test
    public void whenWeCreateSaveStudentInBDViaHibernate() {
        CreateStudentDemo studentDemo = new CreateStudentDemo();
        Student student = new Student("John", "Smith", "john@gmail.com");
        studentDemo.save(student);
        assertEquals(student.getId(), 0);
    }
}