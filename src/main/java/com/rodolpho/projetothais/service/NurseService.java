package com.rodolpho.projetothais.service;

import com.rodolpho.projetothais.payload.NurseDto;
import com.rodolpho.projetothais.payload.NurseResponse;

public interface NurseService {
    
    NurseDto createNurse(NurseDto nurseDto);

    NurseResponse getAllNurses(int pageNo, int pageSize, String sortBy, String sortDir);

    NurseDto getSingleNurse(Long nurseId);

    NurseDto updateNurse(Long nurseId, NurseDto nurseDto);

    void deleteNurse(Long nurseId);
}
