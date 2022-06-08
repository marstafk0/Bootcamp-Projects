package com.marstafk.IHMtrackerTool.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "jogathon_master")
public class JogathonMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "run_date", nullable = false)
    private LocalDate runDate;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Column(name = "comments", nullable = true)
    private String comments;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "jogathon_master_person_laps",
            joinColumns = {
                    @JoinColumn(name = "jogathon_master_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "person_laps_id")})
    private List<PersonLaps> laps;

    public JogathonMaster(LocalDate runDate, boolean active, String comments, List<PersonLaps> laps) {
        this.runDate = runDate;
        this.active = active;
        this.comments = comments;
        this.laps = laps;
    }
}
