package com.marstafk.IHMtrackerTool.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "person_laps")
public class PersonLaps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "laps", nullable = false)
    private int laps;

    public PersonLaps(int laps) {
        this.laps = laps;
    }

}
