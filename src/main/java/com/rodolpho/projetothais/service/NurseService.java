package com.rodolpho.projetothais.service;

import java.util.List;

import com.rodolpho.projetothais.payload.NurseDto;

public interface NurseService {
    
    NurseDto createNurse(NurseDto nurseDto);

    List<NurseDto> getAllNurses(int pageNo, int pageSize);

    NurseDto getSingleNurse(Long nurseId);

    NurseDto updateNurse(Long nurseId, NurseDto nurseDto);

    void deleteNurse(Long nurseId);
}
