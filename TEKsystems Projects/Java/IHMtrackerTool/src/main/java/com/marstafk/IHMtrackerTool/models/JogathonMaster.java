package com.marstafk.IHMtrackerTool.models;

import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinTable(name = "jogathon_master_run",
            joinColumns = {
                    @JoinColumn(name = "jogathon_master_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "run_id")})
    private List<Run> runs;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinTable(name = "jogathon_master_pledge",
            joinColumns = {
                    @JoinColumn(name = "jogathon_master_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "pledge_id")})
    private List<Pledge> pledges;

    public JogathonMaster(LocalDate runDate, boolean active, String comments, List<Run> laps) {
        this.runDate = runDate;
        this.active = active;
        this.comments = comments;
        this.runs = laps;
    }

    public void addRuns(Run run) {
        this.runs.add(run);
    }

    public void addPledges(Pledge pledge) {
        this.pledges.add(pledge);
    }

}
