package zhekbland.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zhekbland.demo.entity.Course;
import zhekbland.demo.entity.Instructor;
import zhekbland.demo.entity.InstructorDetail;
import zhekbland.demo.entity.Review;
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

    /*
     * Save instructors and courses and review for them
     * and display instructor's courses via Eager loading.
     */
    @Test
    public void whenWeCreateAndSaveInstructorsAndCoursesAndReview() {
        Instructor instructor1 = new Instructor("Max", "Maxwell", "max@gmai.com");
        InstructorDetail instructorDetail1 = new InstructorDetail("youtube.com/channel/max", "job");

        Instructor instructor2 = new Instructor("Alex", "Derton", "alex@gmai.com");
        InstructorDetail instructorDetail2 = new InstructorDetail("youtube.com/channel/alex", "fishing");

        Course course1 = new Course("Java Basic");
        Course course2 = new Course("Java Advance");

        Review review1 = new Review("Good job!");
        Review review2 = new Review("Very difficult course!!!");

        int instructorOneId = this.instructorDemo.save(instructor1, instructorDetail1);
        int instructorTwoId = this.instructorDemo.save(instructor2, instructorDetail2);

        int courseId1 = this.instructorDemo.saveCourseForInstructor(course1, review1, instructorOneId);
        int courseId2 = this.instructorDemo.saveCourseForInstructor(course2, review2, instructorTwoId);

        Review resultOne = this.instructorDemo.getReviewViaHQL(courseId1).getReviews().get(0);
        Review resultTwo = this.instructorDemo.getReviewViaHQL(courseId2).getReviews().get(0);

        assertThat(resultOne, is(review1));
        assertThat(resultTwo, is(review2));
    }
}