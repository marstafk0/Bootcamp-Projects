package com.marstafk.IHMtrackerTool.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jogathon_master")
public class JogathonMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 10, max = 10, message = "Please format date as: MM/DD/YYYY")
    @Column(name = "run_date", nullable = false)
    private String runDate;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Size(max = 255, message = "Comments can't be more than 255 characters.")
    @Column(name = "comments", nullable = true)
    private String comments;

    @Column(name = "deletion", nullable = false)
    private boolean deletion;

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

    public JogathonMaster(String s, boolean b, String none, boolean b1, List<Run> runs, List<Pledge> pledges) {
        this.runDate = s;
        this.active = b;
        this.comments = none;
        this.deletion = b1;
        this.runs = runs;
        this.pledges = pledges;
    }

    public void addRuns(Run run) {
        this.runs.add(run);
    }

    public void addPledges(Pledge pledge) {
        this.pledges.add(pledge);
    }

}
