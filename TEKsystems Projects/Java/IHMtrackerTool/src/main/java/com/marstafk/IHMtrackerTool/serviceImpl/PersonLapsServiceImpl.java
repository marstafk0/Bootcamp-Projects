package com.marstafk.IHMtrackerTool.serviceImpl;

import com.marstafk.IHMtrackerTool.models.PersonLaps;
import com.marstafk.IHMtrackerTool.repositories.PersonLapsRepository;
import com.marstafk.IHMtrackerTool.service.PersonLapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonLapsServiceImpl implements PersonLapsService {

    @Autowired
    PersonLapsRepository personLapsRepository;

    @Override
    public PersonLaps getByPersonId(long id) {
        return personLapsRepository.findByPersonId(id);
    }

    @Override
    public  PersonLaps getById(long id) { return personLapsRepository.findById(id).get(); }

    @Override
    public List<PersonLaps> getAllByActive(boolean active) { return personLapsRepository.findAllByActive(active); }

    @Override
    public void savePersonLaps(PersonLaps personLaps) { personLapsRepository.save(personLaps); }
}
