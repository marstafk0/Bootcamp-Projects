/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marstafk.IHMtrackerTool.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author boss_
 */
// Name of class, ie. St. John etc.
@Data
@NoArgsConstructor
@Entity
@Table(name = "classroom")
public class Classroom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 50, message = "Classroom name can't be more than 50 characters.")
    @NotEmpty(message = "Classroom name can't be empty.")
    @Column(name = "class_name", nullable = false)
    private String className;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinTable(name = "classroom_grade",
            joinColumns = {
                    @JoinColumn(name = "classroom_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "grade_id")})
    private List<Grade> grades;

    public Classroom(String className, List<Grade> grades, boolean active) {
        this.className = className;
        this.grades = grades;
        this.active = active;
    }

    public void addGrade(Grade grade) {
        this.grades.add(grade);
    }

}
