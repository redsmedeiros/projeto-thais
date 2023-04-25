package com.rodolpho.projetothais.payload;

import lombok.Data;

@Data
public class NurseDto {
    
    private Long id;

    
    private String email;

   
    private String name;

   
    private String phone;

    
    private String description;

   
    private int age;

    
    private String gender;

   
    private String city;

    
    private String graduation;

   
    private int experienceYears;
}
