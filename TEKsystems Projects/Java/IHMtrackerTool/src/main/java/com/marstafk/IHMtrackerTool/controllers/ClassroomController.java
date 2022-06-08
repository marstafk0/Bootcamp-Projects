package com.marstafk.IHMtrackerTool.controllers;

import com.marstafk.IHMtrackerTool.models.Classroom;
import com.marstafk.IHMtrackerTool.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClassroomController {

    @Autowired
    ClassroomService classroomService;

    @GetMapping("classrooms")
    public Model displayClassRooms(Model model) {
        List<Classroom> classrooms = classroomService.getAllClassrooms();
        model.addAttribute("classrooms", classrooms);
        return model;
    }

}
