/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.models.PersonType;

import java.util.List;

/**
 *
 * @author boss_
 */
public interface PersonTypeService {
    
    public void savePersonType(PersonType personType);
    
    public PersonType getPersonTypeByName(String statusName);

    public List<PersonType> getAllPersonTypes();

    public PersonType getPersonTypeById(long id);

    public List<PersonType> getAllByPersonId(long id);
    
}
