package com.rodolpho.projetothais.payload;

import lombok.Data;

@Data
public class PatientDto {
    
    private long id;

    private String name;

    private String email;

    private String phone;

    private String gender;

    private int age;

    private boolean activated;

    private String city;

}
