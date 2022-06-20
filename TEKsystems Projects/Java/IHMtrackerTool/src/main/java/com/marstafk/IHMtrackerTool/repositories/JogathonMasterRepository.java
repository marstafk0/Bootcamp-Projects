package com.marstafk.IHMtrackerTool.repositories;

import com.marstafk.IHMtrackerTool.models.JogathonMaster;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JogathonMasterRepository extends JpaRepository<JogathonMaster, Long> {
    public List<JogathonMaster> findAllByActive(boolean active);
    public JogathonMaster findByActive(boolean active);
    
}
