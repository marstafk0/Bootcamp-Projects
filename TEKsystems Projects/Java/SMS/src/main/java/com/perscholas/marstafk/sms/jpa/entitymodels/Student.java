/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.perscholas.marstafk.sms.jpa.entitymodels;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author boss_
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {

    @Id
    @Column(name = "email")
    private String sEmail;

    @Column(name = "name")
    private String sName;

    @Column(name = "password")
    private String sPass;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "student_courses",
            joinColumns = {
                @JoinColumn(name = "student_email")},
            inverseJoinColumns = {
                @JoinColumn(name = "course_id")})
    private List<Course> sCourses;

    public Student() {
        super();
    }

    public Student(String sEmail, String sName, String sPass, List<Course> sCourses) {
        super();
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPass = sPass;
        this.sCourses = sCourses;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPass() {
        return sPass;
    }

    public void setsPass(String sPass) {
        this.sPass = sPass;
    }

    public List<Course> getsCourses() {
        return sCourses;
    }

    public void setsCourses(List<Course> sCourses) {
        this.sCourses = sCourses;
    }

    public String getSEmail() {
        return sEmail;
    }

    public void setSEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public void addCourse(Course course) {
        sCourses.add(course);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.sEmail);
        hash = 53 * hash + Objects.hashCode(this.sName);
        hash = 53 * hash + Objects.hashCode(this.sPass);
        hash = 53 * hash + Objects.hashCode(this.sCourses);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (!Objects.equals(this.sEmail, other.sEmail)) {
            return false;
        }
        if (!Objects.equals(this.sName, other.sName)) {
            return false;
        }
        if (!Objects.equals(this.sPass, other.sPass)) {
            return false;
        }
        return Objects.equals(this.sCourses, other.sCourses);
    }

}
