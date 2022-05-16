/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.perscholas.marstafk.sms.jpa.service;

import com.perscholas.marstafk.sms.jpa.dao.StudentDao;
import com.perscholas.marstafk.sms.jpa.entitymodels.Course;
import com.perscholas.marstafk.sms.jpa.entitymodels.Student;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author boss_
 */
public class StudentService implements StudentDao {

    SessionFactory factory;
    Session session;
    Transaction t;
    CriteriaBuilder builder;

    public StudentService() {
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        t = session.beginTransaction();
        builder = session.getCriteriaBuilder();
    }

    @Override
    public List<Student> getAllStudents() {
        CriteriaQuery<Student> criteria = builder.createQuery(Student.class);
        criteria.from(Student.class);
        return session.createQuery(criteria).getResultList();
    }

    @Override
    public Student getStudentByEmail(String sEmail) {
        return session.load(Student.class, sEmail);
    }

    @Override
    public boolean validateStudent(String sEmail, String sPassword) {
        Student s;
        boolean cont;

        try {
            s = session.load(Student.class, sEmail);
            cont = s.getsPass().equals(sPassword);
        } catch (Exception e) {
            return false;
        }
        return cont;
    }

    @Override
    public void registerStudentToCourse(String sEmail, int cId) {
        try {

            Student s = session.load(Student.class, sEmail);
            Course c = session.load(Course.class, cId);
            s.addCourse(c);
            session.save(s);
            t.commit();

        } catch (NonUniqueObjectException e) {
            System.out.println("\nThis Course has already been assigned to you.\n");
        } catch (NumberFormatException j) {
            System.out.println("\nInvalid Course ID.\n");
        }
    }

    @Override
    public List<Course> getStudentCourses(String sEmail) {
        Student s = session.load(Student.class, sEmail);
        return s.getsCourses();
    }

    public void closeSesh() {
        session.close();
    }

}
