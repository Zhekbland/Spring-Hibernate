package zhekbland.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import zhekbland.hibernate.demo.entity.Instructor;
import zhekbland.hibernate.demo.entity.InstructorDetail;

import java.util.List;

public class CreateInstructorDemo {

    private static final CreateInstructorDemo INSTANCE = new CreateInstructorDemo();
    private final SessionFactory factory;

    public CreateInstructorDemo() {
        this.factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
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

    public Instructor findById(int id) {
        Instructor instructor = null;
        try (Session session = this.factory.getCurrentSession()) {
            session.beginTransaction();
            instructor = session.get(Instructor.class, id);
            session.getTransaction().commit();
        }
        return instructor;
    }

    /*
     * Also will be delete InstructorDetail
     * because we use cascade.All method.
     */
    public boolean deleteInstructor(int id) {
        boolean result = true;
        try (Session session = this.factory.getCurrentSession()) {
            session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, id);
            session.delete(instructor);
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
