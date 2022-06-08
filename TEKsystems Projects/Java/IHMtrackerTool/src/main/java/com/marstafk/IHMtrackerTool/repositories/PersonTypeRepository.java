/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author boss_
 */
public interface PersonTypeRepository extends JpaRepository<PersonType, Long>{

    public PersonType findByStatusName(String statusName);
    
}