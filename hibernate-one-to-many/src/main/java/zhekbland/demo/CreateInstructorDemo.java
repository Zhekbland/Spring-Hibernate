package zhekbland.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import zhekbland.demo.entity.Course;
import zhekbland.demo.entity.Instructor;
import zhekbland.demo.entity.InstructorDetail;

import java.util.List;

public class CreateInstructorDemo {

    private static final CreateInstructorDemo INSTANCE = new CreateInstructorDemo();
    private final SessionFactory factory;

    public CreateInstructorDemo() {
        this.factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();
    }

    public static CreateInstructorDemo getInstance() {
        return INSTANCE;
    }

    public int save(Instructor instructor, InstructorDetail instructorDetail) {
        try (Session session = this.factory.getCurrentSession()) {
            session.beginTransaction();
            instructor.setInstructorDetail(instructorDetail);
            session.save(instructor);
            session.getTransaction().commit();
        }
        return instructor.getId();
    }

    public int saveCourseForInstructor(Course course, int instructorId) {
        try (Session session = this.factory.getCurrentSession()) {
            session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, instructorId);
            instructor.add(course);
            session.save(course);
            session.getTransaction().commit();
        }
        return course.getId();
    }

    public List<Course> getInstructorCourses(int instructorId) {
        List<Course> courses = null;
        try (Session session = this.factory.getCurrentSession()) {
            session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, instructorId);
            courses = instructor.getCourses();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    public Instructor getInstructorCoursesViaHQL(int instructorId) {
        Instructor instructor = null;
        try (Session session = this.factory.getCurrentSession()) {
            session.beginTransaction();
            Query<Instructor> query = session.createQuery("select i from Instructor i "
                    + "join fetch  i.courses "
                    + "where i.id=:instructorId", Instructor.class);
            query.setParameter("instructorId", instructorId);
            instructor = query.getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ough Fault!!");
            e.printStackTrace();
        }
        return instructor;
    }

    /*
     * Also will be delete InstructorDetail
     * because we use cascade.All method.
     */
    public boolean deleteCourse(int courseId) {
        boolean result = true;
        try (Session session = this.factory.getCurrentSession()) {
            session.beginTransaction();
            Course course = session.get(Course.class, courseId);
            session.delete(course);
            session.getTransaction().commit();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /*
     * Will be deleted only InstructorDetail
     * because we use cascade without remove AND
     * interrupt bidirectional communication.
     */
    public boolean deleteInstructorDetail(int id) {
        boolean result = true;
        try (Session session = this.factory.getCurrentSession()) {
            session.beginTransaction();
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);
            instructorDetail.getInstructor().setInstructorDetail(null);
            session.delete(instructorDetail);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /*
     * Here we check Bidirectional communication.
     */
    public Instructor findByInstructorDetailId(int instructorDetailId) {
        Instructor instructor;
        InstructorDetail instructorDetail;
        try (Session session = this.factory.getCurrentSession()) {
            session.beginTransaction();
            instructorDetail = session.get(InstructorDetail.class, instructorDetailId);
            instructor = instructorDetail.getInstructor();
            session.getTransaction().commit();
        }
        return instructor;
    }

    /*
     * Here we check Bidirectional communication.
     */
    public InstructorDetail findByInstructorDetail(int instructorDetailId) {
        InstructorDetail instructorDetail;
        try (Session session = this.factory.getCurrentSession()) {
            session.beginTransaction();
            instructorDetail = session.get(InstructorDetail.class, instructorDetailId);
            session.getTransaction().commit();
        }
        return instructorDetail;
    }
}
