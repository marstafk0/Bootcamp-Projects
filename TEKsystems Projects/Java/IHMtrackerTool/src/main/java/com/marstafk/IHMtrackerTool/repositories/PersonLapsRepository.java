package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.PersonLaps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonLapsRepository extends JpaRepository<PersonLaps, Long> {

    @Query(value = "SELECT pl.* FROM person_laps pl JOIN person_person_laps ppl ON ppl.person_laps_id = pl.id JOIN " +
            "person p ON p.id = ppl.person_id WHERE p.id = :id AND p.active = true AND pl.active = true", nativeQuery = true)
    PersonLaps findByPersonId(long id);

    List<PersonLaps> findAllByActive(boolean active);

}
