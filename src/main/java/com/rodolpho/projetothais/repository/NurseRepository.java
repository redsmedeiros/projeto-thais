package com.rodolpho.projetothais.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rodolpho.projetothais.entity.Nurse;

public interface NurseRepository extends JpaRepository<Nurse, Long> {

    @Query("SELECT COUNT(n) FROM Nurse n WHERE n.experienceYears > :experienceYears")
    Long countNursesWithExperienceGreaterThan(@Param("experienceYears") int experienceYears);

    @Query("SELECT n FROM Nurse n WHERE n.gender = 'feminino'")
    List<Nurse> findAllFemaleNurses();

    @Query("SELECT n FROM Nurse n WHERE n.gender = 'masculino'")
    List<Nurse> findAllMaleNurses();

    //A cláusula CASE é usada para transformar a propriedade gender em um valor numérico 1 ou 0, dependendo se a enfermeira é do sexo feminino ou não. A função AVG é usada para calcular a média dos valores 1 e 0.
    @Query("SELECT AVG(CASE WHEN n.gender = 'feminino' THEN 1 ELSE 0 END) FROM Nurse n")
    Double findAverageFemaleNurses();
    
}
