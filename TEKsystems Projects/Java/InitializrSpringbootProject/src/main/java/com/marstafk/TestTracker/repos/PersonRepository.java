/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marstafk.TestTracker.repos;

import com.marstafk.TestTracker.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author boss_
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
    
}
