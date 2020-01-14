package zhekbland.hibernate.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zhekbland.hibernate.demo.entity.Instructor;
import zhekbland.hibernate.demo.entity.InstructorDetail;
import zhekbland.jdbc.DataOneToOne;
import zhekbland.jdbc.DatabaseCreator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CreateInstructorDemoTest {

    private final DatabaseCreator databaseCreator = DataOneToOne.getInstance();

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
     * Save the instructor.
     * Note: this will ALSO save the InstructorDetail object
     * because of CascadeType.ALL
     */
    @Test
    public void whenWeCreateAndSaveInstructor() {
        Instructor instructor1 = new Instructor("Max", "Maxwell", "max@gmai.com");
        InstructorDetail instructorDetail1 = new InstructorDetail("youtube.com/channel/max", "job");

        Instructor instructor2 = new Instructor("Alex", "Derton", "alex@gmai.com");
        InstructorDetail instructorDetail2 = new InstructorDetail("youtube.com/channel/alex", "fishing");

        this.instructorDemo.save(instructor1, instructorDetail1);
        this.instructorDemo.save(instructor2, instructorDetail2);
    }

    /*
     * Also will be delete InstructorDetail
     * because we use cascade.All method.
     */
    @Test
    public void whenWeGetAndDeleteInstructor() {
        Instructor instructor1 = new Instructor("John", "Popovich", "john@gmai.com");
        InstructorDetail instructorDetail1 = new InstructorDetail("youtube.com/channel/john", "play");

        Instructor instructor2 = new Instructor("Sam", "Smith", "sam@gmai.com");
        InstructorDetail instructorDetail2 = new InstructorDetail("youtube.com/channel/sam", "dream");

        int instructorOneId = this.instructorDemo.save(instructor1, instructorDetail1);
        int instructorTwoId = this.instructorDemo.save(instructor2, instructorDetail2);

        this.instructorDemo.deleteInstructor(instructorOneId);

        Instructor resultInstructorOne = this.instructorDemo.findById(instructorOneId);
        Instructor resultInstructorTwo = this.instructorDemo.findById(instructorTwoId);

        assertNull(resultInstructorOne);
        assertThat(resultInstructorTwo, is(instructor2));
    }

    @Test
    public void whenWeTestBiDirectional() {
        Instructor instructor1 = new Instructor("John", "Popovich", "john@gmai.com");
        InstructorDetail instructorDetail1 = new InstructorDetail("youtube.com/channel/john", "play");

        int instructorOneId = this.instructorDemo.save(instructor1, instructorDetail1);
        Instructor result = this.instructorDemo.findByInstructorDetailId(instructorDetail1.getId());

        assertThat(result, is(instructor1));
    }

    /*
     * Will be deleted only InstructorDetail.
     */
    @Test
    public void whenWeDeleteOnlyInstructorDetail() {
        Instructor instructor1 = new Instructor("John", "Popovich", "john@gmai.com");
        InstructorDetail instructorDetail1 = new InstructorDetail("youtube.com/channel/john", "play");

        int instructorOneId = this.instructorDemo.save(instructor1, instructorDetail1);

        this.instructorDemo.deleteInstructorDetail(instructorDetail1.getId());

        InstructorDetail resultOne = this.instructorDemo.findByInstructorDetail(instructorDetail1.getId());
        Instructor resultTwo = this.instructorDemo.findById(instructorOneId);
        assertNull(resultOne);
        assertNotNull(resultTwo);
    }
}