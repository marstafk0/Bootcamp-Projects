/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marstafk.IHMtrackerTool.serviceImpl;

import com.marstafk.IHMtrackerTool.models.PersonType;
import com.marstafk.IHMtrackerTool.repositories.PersonTypeRepository;
import com.marstafk.IHMtrackerTool.service.PersonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author boss_
 */
@Service
public class PersonTypeServiceImpl implements PersonTypeService{

    @Autowired
    PersonTypeRepository personTypeRepository;
    
    @Override
    public void savePersonType(PersonType personType) {
        personTypeRepository.save(personType);
    }

    @Override
    public PersonType getPersonTypeByName(String statusName) {
        return personTypeRepository.findByStatusName(statusName);
    }
}
