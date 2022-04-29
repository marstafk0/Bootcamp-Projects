/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.marstafk.todoapplication.data;

import com.sg.marstafk.todoapplication.models.ToDo;
import java.util.List;

/**
 *
 * @author boss_
 */
public interface ToDoDao {
    
    ToDo add(ToDo todo);

    List<ToDo> getAll();

    ToDo findById(int id);

    // true if item exists and is updated
    boolean update(ToDo todo);

    // true if item exists and is deleted
    boolean deleteById(int id);
    
}
