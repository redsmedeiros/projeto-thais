package com.rodolpho.projetothais.payload;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NurseDto {
    
    private Long id;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 2, message = "Email should have at least 2 characters")
    private String name;

    @NotEmpty
    private String phone;

    
    private String description;

    @NotEmpty
    private int age;

    @NotEmpty
    private String gender;

    @NotEmpty
    private String city;

    @NotEmpty
    private String graduation;

    @NotEmpty
    private int experienceYears;

    private Set<PatientDto> patients;
}
