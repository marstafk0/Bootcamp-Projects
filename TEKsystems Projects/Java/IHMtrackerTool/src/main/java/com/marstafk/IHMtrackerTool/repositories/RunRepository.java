package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.Run;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RunRepository extends JpaRepository<Run, Long> {

    @Query(value = "SELECT r.* FROM person p " +
            "JOIN person_run pr ON p.id = pr.person_id " +
            "JOIN run r ON r.id = pr.run_id " +
            "JOIN jogathon_master_run jmr ON jmr.run_id = r.id " +
            "JOIN jogathon_master jm ON jmr.jogathon_master_id = jm.id " +
            "WHERE p.id = :id AND jm.active = true", nativeQuery = true)
    Run findByPersonId(long id);

//    @Query(value = "SELECT r.* FROM run r JOIN jogathon_master_run jmr ON jmr.run_id = r.id JOIN jogathon_master jm ON " +
//            "jm.id = jmr.jogathon_master_id WHERE jm.active = :active", nativeQuery = true)
//    List<Run> findAllByActive(boolean active);

}
