/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.perscholas.marstafk.sms.jpa.service;

import com.perscholas.marstafk.sms.jpa.dao.StudentDao;
import com.perscholas.marstafk.sms.jpa.entitymodels.Student;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author boss_
 */
public class StudentServiceTest {
    
    StudentDao s = new StudentService();
    
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    Session session = factory.openSession();
    Transaction t = session.beginTransaction();
    CriteriaBuilder builder = session.getCriteriaBuilder();
    
    public StudentServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
        session.close();
    }

    /**
     * Test of getAllStudents method, of class StudentService.
     */
    @org.junit.jupiter.api.Test
    public void testGetAllStudents() {
        List<Student> st = s.getAllStudents();
        
        CriteriaQuery<Student> criteria = builder.createQuery(Student.class);
        criteria.from(Student.class);
        
        List<Student> st2 = session.createQuery(criteria).getResultList();
        
        assertNotNull(st);
        assertEquals(st2.get(0).getsName(), st.get(0).getsName());
        assertEquals(st2.get(0).getsEmail(), st.get(0).getsEmail());
        assertEquals(st2.get(0).getsPass(), st.get(0).getsPass());
        assertEquals(st.get(1).getsPass(), "FnVklVgC6r6");
    }

    /**
     * Test of getStudentByEmail method, of class StudentService.
     */
    @org.junit.jupiter.api.Test
    public void testGetStudentByEmail() {
        Student st = s.getStudentByEmail("aiannitti7@is.gd");
        
        Student st2 = session.load(Student.class, "aiannitti7@is.gd");
        
        assertNotNull(st);
        assertEquals(st.getsName(), st2.getsName());
        assertEquals(st.getsEmail(), st2.getsEmail());
        assertEquals(st.getsPass(), st2.getsPass());
    }

    /**
     * Test of validateStudent method, of class StudentService.
     */
    @org.junit.jupiter.api.Test
    public void testValidateStudent() {
        boolean f = s.validateStudent("fewgf", "fewhbf");
        
        assertFalse(f);
    }

    /**
     * Test of getStudentCourses method, of class StudentService.
     */
    @org.junit.jupiter.api.Test
    public void testGetStudentCourses() {
        List<Student> st = s.getAllStudents();
        CriteriaQuery<Student> criteria = builder.createQuery(Student.class);
        criteria.from(Student.class);
        List<Student> st2 = session.createQuery(criteria).getResultList();
        
        assertNotNull(st);
        assertEquals(st.get(0).getsName(), st2.get(0).getsName());
        assertEquals(st.get(st.size()-1).getSEmail(), st2.get(st2.size()-1).getSEmail());
        assertEquals(st.size(), st2.size());
    }
    
}
