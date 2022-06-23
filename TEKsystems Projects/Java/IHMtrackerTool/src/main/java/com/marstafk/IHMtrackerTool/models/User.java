package com.marstafk.IHMtrackerTool.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Email can't be empty.")
    @Email(message = "Please enter a proper email.")
    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @NotEmpty(message = "Password can't be empty")
    @Size(min = 5, message = "Password must be more than 5 characters.")
    @Column(nullable = false)
    private String password;

    @NotEmpty(message = "First name can't be empty.")
    @Size(max = 20, message = "First name must be between 2 and 20 characters.")
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @NotEmpty(message = "Last name can't be empty.")
    @Size(max = 20, message = "Last name must be between 2 and 20 characters.")
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

}
