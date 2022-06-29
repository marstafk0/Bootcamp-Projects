package com.marstafk.IHMtrackerTool.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    private long studentId;
    private String student;
    private BigDecimal total;

}
