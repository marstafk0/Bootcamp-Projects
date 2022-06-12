package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.models.PersonLaps;

import java.util.List;

public interface PersonLapsService {

    PersonLaps getByPersonId(long id);

    List<PersonLaps> getAllByActive(boolean active);

    void savePersonLaps(PersonLaps personLaps);

    PersonLaps getById(long id);

}
