/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.perscholas.marstafk.sms.jpa.entitymodels;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author boss_
 */
@Entity
@Table(name = "course")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int cId;

    @Column(name = "name")
    private String cName;

    @Column(name = "instructor")
    private String cInstructorName;

    public Course() {
        super();
    }

    public Course(int cId, String cName, String cInstructorName) {
        super();
        this.cId = cId;
        this.cName = cName;
        this.cInstructorName = cInstructorName;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcInstructorName() {
        return cInstructorName;
    }

    public void setcInstructorName(String cInstructorName) {
        this.cInstructorName = cInstructorName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.cId;
        hash = 97 * hash + Objects.hashCode(this.cName);
        hash = 97 * hash + Objects.hashCode(this.cInstructorName);
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
        final Course other = (Course) obj;
        if (this.cId != other.cId) {
            return false;
        }
        if (!Objects.equals(this.cName, other.cName)) {
            return false;
        }
        return Objects.equals(this.cInstructorName, other.cInstructorName);
    }

}
