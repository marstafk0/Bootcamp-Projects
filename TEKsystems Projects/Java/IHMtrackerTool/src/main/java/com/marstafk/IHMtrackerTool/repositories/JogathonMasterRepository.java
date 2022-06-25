package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.JogathonMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JogathonMasterRepository extends JpaRepository<JogathonMaster, Long> {
    JogathonMaster findByActiveAndDeletion(boolean active, boolean deletion);

}
