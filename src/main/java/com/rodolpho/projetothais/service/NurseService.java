package com.rodolpho.projetothais.service;

import java.util.List;

import com.rodolpho.projetothais.payload.NurseDto;

public interface NurseService {
    
    NurseDto createNurse(NurseDto nurseDto);

    List<NurseDto> getAllNurses();

    NurseDto getSingleNurse(Long nurseId);
}
