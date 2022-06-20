/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marstafk.IHMtrackerTool.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author boss_
 */
@Data
@AllArgsConstructor
public class Student {
    
    private Long id; 
    private String firstName;
    private String lastName;
    private String contact;
    private String gradeName;
    private String classroom;

}
