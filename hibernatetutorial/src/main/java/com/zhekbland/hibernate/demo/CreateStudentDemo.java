package com.zhekbland.hibernate.demo;

import com.zhekbland.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class CreateStudentDemo implements AutoCloseable {

    private static final CreateStudentDemo INSTANCE = new CreateStudentDemo();
    private final SessionFactory factory;

    public CreateStudentDemo() {
        this.factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
    }

    public static CreateStudentDemo getInstance() {
        return INSTANCE;
    }

    public int save(Student student) {
        try (Session session = this.factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
        }
        return student.getId();
    }

    public Student get(int id) {
        Student student = null;
        try (Session session = this.factory.getCurrentSession()) {
            session.beginTransaction();
            student = session.get(Student.class, id);
            session.getTransaction().commit();
        }
        return student;
    }

    public List<Student> getAll() {
        List<Student> student;
        try (Session session = this.factory.getCurrentSession()) {
            session.beginTransaction();
            student = session.createQuery("from Student").getResultList();
            session.getTransaction().commit();
        }
        return student;
    }

    public Student findByEmail(String email) {
        Student student;
        try (Session session = this.factory.getCurrentSession()) {
            session.beginTransaction();
            student = (Student) session.createQuery("from Student s where s.email= '" + email + "'").getSingleResult();
            session.getTransaction().commit();
        }
        return student;
    }

    public List<Student> findWithMail(String mail) {
        List<Student> student;
        try (Session session = this.factory.getCurrentSession()) {
            session.beginTransaction();
            student = session.createQuery("from Student s where s.email LIKE '%" + mail + "'").getResultList();
            session.getTransaction().commit();
        }
        return student;
    }


    @Override
    public void close() {
        if (this.factory != null) {
            this.factory.close();
        }
    }
}
