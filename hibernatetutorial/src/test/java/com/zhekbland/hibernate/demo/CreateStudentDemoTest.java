package com.zhekbland.hibernate.demo;

import com.zhekbland.hibernate.demo.entity.Student;
import com.zhekbland.jdbc.DataCreator;
import com.zhekbland.jdbc.DatabaseCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CreateStudentDemoTest {

    private final CreateStudentDemo studentDemo = CreateStudentDemo.getInstance();
    private final DatabaseCreator dataCreator = DataCreator.getInstance();

    @Before
    public void init() {
        this.dataCreator.checkTableExist();
    }

    @After
    public void destroy() {
        this.dataCreator.deleteTable();
    }

    @Test
    public void whenWeCreateAndGetStudent() {
        Student student = new Student("Max", "Sorken", "max@gmail.com");
        this.studentDemo.save(student);
        Student result = this.studentDemo.get(student.getId());
        assertEquals(result.getFirstName(), "Max");
        assertEquals(result.getLastName(), "Sorken");
        assertEquals(result.getEmail(), "max@gmail.com");
    }

    /**
     * We save students and use query (HQL):
     * 1. Get them all
     * 2. find by email
     * 3. find by gmail
     */
    @Test
    public void whenWeCreateAndGetListOfStudents() {
        Student student1 = new Student("John", "Stone", "john@gmail.com");
        Student student2 = new Student("Pit", "Rebbel", "pit@gmail.com");
        Student student3 = new Student("Sam", "Aston", "sam@gmail.com");
        this.studentDemo.save(student1);
        this.studentDemo.save(student2);
        this.studentDemo.save(student3);
        List<Student> result = this.studentDemo.getAll();
        List<Student> expected = List.of(student1, student2, student3);
        Student resultEmail = this.studentDemo.findByEmail(student3.getEmail());
        List<Student> resultGmailList = this.studentDemo.findWithMail("gmail.com");
        assertThat(result, is(expected));
        assertThat(resultEmail.getEmail(), is(student3.getEmail()));
        assertThat(resultGmailList, is(expected));
    }

    /**
     * We update student.
     */
    @Test
    public void whenWeCreateAndUpdateStudent() {
        Student student = new Student("John", "Stone", "john@gmail.com");
        int studentId = this.studentDemo.save(student);
        String newEmail = "stone@gmail.com";
        Student resultUpdate = this.studentDemo.updateStudentEmail(studentId, newEmail);
        assertThat(resultUpdate.getEmail(), is(newEmail));
    }

    /**
     * We delete student:
     * 1. Via HQL
     * 2. Via delete method
     */
    @Test
    public void whenWeCreateAndDeleteStudent() {
        Student student1 = new Student("John", "Stone", "john@gmail.com");
        Student student2 = new Student("Sam", "Smith", "sam@gmail.com");
        int student1Id = this.studentDemo.save(student1);
        int student2Id = this.studentDemo.save(student2);
        boolean resultOne = this.studentDemo.deleteStudentViaHQL(student1Id);
        boolean resultTwo = this.studentDemo.deleteStudent(student2Id);
        assertThat(resultOne, is(true));
        assertThat(resultTwo, is(true));
    }

    /**
     * We delete student:
     * 1. Via HQL
     * 2. Via delete method
     */
    @Test
    public void whenWeCreateStudentWithDateOfBirth() {
        String dateOfBirthStr = "23/01/1992";
        Date dateOfBirth = DateUtils.parseDate(dateOfBirthStr);
        Student student = new Student("John", "Stone", "john@gmail.com", dateOfBirth);
        int studentId = this.studentDemo.save(student);
        Student result = this.studentDemo.get(studentId);
        String resultDateOfBirth = DateUtils.formatDate(result.getDateOfBirth());
        assertThat(resultDateOfBirth, is(dateOfBirthStr));
    }
}