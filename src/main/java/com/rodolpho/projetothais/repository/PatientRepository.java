package com.rodolpho.projetothais.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodolpho.projetothais.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByNurseId(long id);
    
}
