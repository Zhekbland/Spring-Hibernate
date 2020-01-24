package zhekbland.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import zhekbland.demo.entity.*;

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
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
    }

    public static CreateInstructorDemo getInstance() {
        return INSTANCE;
    }

    public int saveCourse(Course course) {
        try (Session session = this.factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(course);
            session.getTransaction().commit();
        }
        return course.getId();
    }

    public int saveStudent(Student student) {
        try (Session session = this.factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
        }
        return student.getId();
    }

    public void saveCourseForStudent(int studentId, int courseId) {
        try (Session session = this.factory.getCurrentSession()) {
            session.beginTransaction();
            Course course = session.get(Course.class, courseId);
            Student student = session.get(Student.class, studentId);
            course.addStudent(student);
            session.getTransaction().commit();
        }
//        return student.getId();
    }

    public Student getStudentHQL(int studentId) {
        Student student = null;
        try (Session session = this.factory.getCurrentSession()) {
            session.beginTransaction();
            Query<Student> query = session.createQuery("select s from Student s "
            + "join fetch s.courses "
            + "where s.id=:studentId", Student.class);
            query.setParameter("studentId", studentId);
            student = query.getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public void deleteCourse(int courseId) {
        try (Session session = this.factory.getCurrentSession()) {
            session.beginTransaction();
            Course course = session.get(Course.class, courseId);
            session.delete(course);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
