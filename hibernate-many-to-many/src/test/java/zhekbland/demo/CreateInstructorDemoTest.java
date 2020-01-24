package zhekbland.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zhekbland.demo.entity.Course;
import zhekbland.demo.entity.Student;
import zhekbland.jdbc.DataManyToMany;
import zhekbland.jdbc.DatabaseCreator;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CreateInstructorDemoTest {

    private final DatabaseCreator databaseCreator = DataManyToMany.getInstance();

    private final CreateInstructorDemo instructorDemo = CreateInstructorDemo.getInstance();

    @Before
    public void init() {
        this.databaseCreator.checkTableExist();
    }

    @After
    public void destroy() {
        this.databaseCreator.deleteTable();
    }

    /*
     * Save students and courses for them
     * and display student's courses via join fetch.
     */
    @Test
    public void whenWeCreateAndSaveStudetnAndCourses() {
        Student studentOne = new Student("John", "Sparrow", "john@gmail.com");
        Student studentTwo = new Student("Linda", "Horn", "linda@gmail.com");
        Course courseOne = new Course("Java Basic");
        Course courseTwo = new Course("Java Advance");

        int courseId1 = this.instructorDemo.saveCourse(courseOne);
        int courseId2 = this.instructorDemo.saveCourse(courseTwo);

        int studentIdOne = this.instructorDemo.saveStudent(studentOne);
        int studentIdTwo = this.instructorDemo.saveStudent(studentTwo);

        this.instructorDemo.saveCourseForStudent(studentIdOne, courseId1);
        this.instructorDemo.saveCourseForStudent(studentIdOne, courseId2);
        this.instructorDemo.saveCourseForStudent(studentIdTwo, courseId1);

        List<Course> result = this.instructorDemo.getStudentHQL(studentIdOne).getCourses();

        assertThat(result.size(), is(2));
    }

    /*
     * Save students and courses for them
     * and display student's courses via join fetch.
     */
    @Test
    public void whenWeDeleteCourse() {
        Student studentOne = new Student("John", "Sparrow", "john@gmail.com");
        Student studentTwo = new Student("Linda", "Horn", "linda@gmail.com");
        Course courseOne = new Course("Java Basic");
        Course courseTwo = new Course("Java Advance");

        int courseId1 = this.instructorDemo.saveCourse(courseOne);
        int courseId2 = this.instructorDemo.saveCourse(courseTwo);

        int studentIdOne = this.instructorDemo.saveStudent(studentOne);
        int studentIdTwo = this.instructorDemo.saveStudent(studentTwo);

        this.instructorDemo.saveCourseForStudent(studentIdOne, courseId1);
        this.instructorDemo.saveCourseForStudent(studentIdOne, courseId2);
        this.instructorDemo.saveCourseForStudent(studentIdTwo, courseId1);

        this.instructorDemo.deleteCourse(courseId2);

        List<Course> result = this.instructorDemo.getStudentHQL(studentIdOne).getCourses();

        assertThat(result.size(), is(1));
    }

}