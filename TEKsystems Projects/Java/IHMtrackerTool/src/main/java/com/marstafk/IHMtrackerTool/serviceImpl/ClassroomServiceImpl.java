package com.marstafk.IHMtrackerTool.serviceImpl;

import com.marstafk.IHMtrackerTool.exceptions.ObjectNotFoundException;
import com.marstafk.IHMtrackerTool.models.Classroom;
import com.marstafk.IHMtrackerTool.repositories.ClassroomRepository;
import com.marstafk.IHMtrackerTool.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    ClassroomRepository classroomRepository;

    @Override
    public List<Classroom> getAllClassrooms(boolean active) {
        return classroomRepository.findAllByActive(active);
    }

    @Override
    public Classroom getClassroomById(long id) throws ObjectNotFoundException {
        try {
            return classroomRepository.findById(id).get();
        } catch (Exception e) {
            throw new ObjectNotFoundException("Could not get Classroom");
        }
    }

    @Override
    public void saveClassroom(Classroom classroom) {
        classroomRepository.save(classroom);
    }

    @Override
    public Classroom getClassroomByGradeId(long id) throws ObjectNotFoundException {
        try {
            return classroomRepository.findByGradeId(id);
        } catch (Exception e) {
            throw new ObjectNotFoundException("Could not get Classroom by grade");
        }
    }

    @Override
    public void deleteClassroom(long id) {
        classroomRepository.deleteById(id);
    }

}
