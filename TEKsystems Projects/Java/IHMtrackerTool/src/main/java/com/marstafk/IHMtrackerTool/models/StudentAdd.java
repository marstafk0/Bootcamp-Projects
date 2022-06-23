package com.marstafk.IHMtrackerTool.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class StudentAdd {

    private Long id;
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters.")
    @NotEmpty(message = "First Name can't be empty.")
    private String firstName;
    @NotEmpty(message = "First Name can't be empty.")
    private String lastName;
    private String contact;
    private String gradeId;
    private String familyId;

}