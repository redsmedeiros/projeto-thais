package com.rodolpho.projetothais.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodolpho.projetothais.entity.Nurse;

public interface NurseRepository extends JpaRepository<Nurse, Long> {
    
}
