package com.rodolpho.projetothais.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodolpho.projetothais.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    
}
