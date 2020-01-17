package zhekbland.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zhekbland.demo.entity.Course;
import zhekbland.demo.entity.Instructor;
import zhekbland.demo.entity.InstructorDetail;
import zhekbland.jdbc.DataOneToMany;
import zhekbland.jdbc.DatabaseCreator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CreateInstructorDemoTest {

    private final DatabaseCreator databaseCreator = DataOneToMany.getInstance();

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
     * Save instructors and courses for them
     * and display instructor's courses via Eager loading.
     */
    @Test
    public void whenWeCreateAndSaveInstructorsAndCourses() {
        Instructor instructor1 = new Instructor("Max", "Maxwell", "max@gmai.com");
        InstructorDetail instructorDetail1 = new InstructorDetail("youtube.com/channel/max", "job");

        Instructor instructor2 = new Instructor("Alex", "Derton", "alex@gmai.com");
        InstructorDetail instructorDetail2 = new InstructorDetail("youtube.com/channel/alex", "fishing");

        Course course1 = new Course("Java Basic");
        Course course2 = new Course("Java Advance");

        int instructorOneId = this.instructorDemo.save(instructor1, instructorDetail1);
        int instructorTwoId = this.instructorDemo.save(instructor2, instructorDetail2);

        this.instructorDemo.saveCourseForInstructor(course1, instructorOneId);
        this.instructorDemo.saveCourseForInstructor(course2, instructorTwoId);

        Course resultOne = this.instructorDemo.getInstructorCoursesViaHQL(instructorOneId).getCourses().get(0);
        Course resultTwo = this.instructorDemo.getInstructorCoursesViaHQL(instructorTwoId).getCourses().get(0);

        assertThat(resultOne, is(course1));
        assertThat(resultTwo, is(course2));
    }

    /*
     * Save instructors and courses for them
     * Delete course 2 and display size instructor's course list. Because after
     * deleting instructor doesn't have any courses.
     */
    @Test
    public void whenWeDeleteCourse() {
        Instructor instructor1 = new Instructor("Max", "Maxwell", "max@gmai.com");
        InstructorDetail instructorDetail1 = new InstructorDetail("youtube.com/channel/max", "job");

        Instructor instructor2 = new Instructor("Alex", "Derton", "alex@gmai.com");
        InstructorDetail instructorDetail2 = new InstructorDetail("youtube.com/channel/alex", "fishing");

        Course course1 = new Course("Java Basic");
        Course course2 = new Course("Java Advance");

        int instructorOneId = this.instructorDemo.save(instructor1, instructorDetail1);
        int instructorTwoId = this.instructorDemo.save(instructor2, instructorDetail2);

        int courseIdOne = this.instructorDemo.saveCourseForInstructor(course1, instructorOneId);
        int courseIdTwo = this.instructorDemo.saveCourseForInstructor(course2, instructorTwoId);

        this.instructorDemo.deleteCourse(courseIdTwo);
    }
}