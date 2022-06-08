/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.models.PersonType;

/**
 *
 * @author boss_
 */
public interface PersonTypeService {
    
    void savePersonType(PersonType personType);
    
    PersonType getPersonTypeByName(String statusName);
    
}
