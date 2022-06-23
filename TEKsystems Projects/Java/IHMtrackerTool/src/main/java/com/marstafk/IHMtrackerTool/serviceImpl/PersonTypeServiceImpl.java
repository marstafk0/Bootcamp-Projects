/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marstafk.IHMtrackerTool.serviceImpl;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.PersonType;
import com.marstafk.IHMtrackerTool.repositories.PersonTypeRepository;
import com.marstafk.IHMtrackerTool.service.PersonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author boss_
 */
@Service
public class PersonTypeServiceImpl implements PersonTypeService {

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

    @Override
    public List<PersonType> getAllPersonTypes() {
        return personTypeRepository.findAllOrderByAsc();
    }

    @Override
    public PersonType getPersonTypeById(long id) throws ObjectNotFoundException {
        try {
            return personTypeRepository.findById(id).get();
        } catch (Exception e) {
            throw new ObjectNotFoundException("Could not retrieve Person Type");
        }
    }

    @Override
    public PersonType getByPersonId(long id) {
        return personTypeRepository.findByPersonId(id);
    }

}
