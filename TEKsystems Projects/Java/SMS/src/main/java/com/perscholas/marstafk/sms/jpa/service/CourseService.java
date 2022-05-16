/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.perscholas.marstafk.sms.jpa.service;

import com.perscholas.marstafk.sms.jpa.dao.CourseDao;
import com.perscholas.marstafk.sms.jpa.entitymodels.Course;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author boss_
 */
public class CourseService implements CourseDao {

    SessionFactory factory;
    Session session;
    Transaction t;
    CriteriaBuilder builder;
    
    public CourseService() {
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        t = session.beginTransaction();
        builder = session.getCriteriaBuilder();
    }

    @Override
    public List<Course> getAllCourses() {
        CriteriaQuery<Course> criteria = builder.createQuery(Course.class);
        criteria.from(Course.class);
        return session.createQuery(criteria).getResultList();
    }

    public Course getCourseById(int id) {
        return session.load(Course.class, id);
    }
    
    public void closeSesh() {
        session.close();
    }
}
