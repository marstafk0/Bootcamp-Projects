package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.Classroom;

import java.util.List;

public interface ClassroomService {

    List<Classroom> getAllClassrooms(boolean active);

    Classroom getClassroomById(long id) throws ObjectNotFoundException;

    void saveClassroom(Classroom classroom);

    Classroom getClassroomByGradeId(long id) throws ObjectNotFoundException;

    void deleteClassroom(long id);

}
