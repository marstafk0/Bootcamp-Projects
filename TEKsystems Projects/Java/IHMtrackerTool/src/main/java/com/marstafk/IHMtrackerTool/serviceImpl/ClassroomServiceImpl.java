package com.marstafk.IHMtrackerTool.serviceImpl;

import com.marstafk.IHMtrackerTool.models.Classroom;
import com.marstafk.IHMtrackerTool.repositories.ClassroomRepository;
import com.marstafk.IHMtrackerTool.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    ClassroomRepository classroomRepository;

    @Override
    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    @Override
    public Classroom getClassroomByName(String classroomName) {
        return classroomRepository.findByClassName(classroomName);
    }

    @Override
    public Classroom getClassroomById(long id) {
        return classroomRepository.findById(id).get();
    }

    @Override
    public void saveClassroom(Classroom classroom) {
        classroomRepository.save(classroom);
    }

    @Override
    public Classroom getClassroomByGradeId(long id) {
        return classroomRepository.findByGradeId(id);
    }
    
}
