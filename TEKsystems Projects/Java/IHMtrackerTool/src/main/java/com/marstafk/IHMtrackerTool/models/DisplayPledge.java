package com.marstafk.IHMtrackerTool.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DisplayPledge {

    private long id;
    private String earner;
    private String sponsor;
    private BigDecimal total;
    private BigDecimal oneTime;
    private BigDecimal perLap;
    private int week;
    private boolean collected;
    private boolean receipt;

}
