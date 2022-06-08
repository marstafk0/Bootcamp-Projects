package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.models.Classroom;

import java.util.List;

public interface ClassroomService {

    List<Classroom> getAllClassrooms();
    Classroom getClassroomByName(String classroomName);
    Classroom getClassroomById(long id);
    void saveClassroom(Classroom classroom);
    Classroom getClassroomByGradeId(long id);

}
