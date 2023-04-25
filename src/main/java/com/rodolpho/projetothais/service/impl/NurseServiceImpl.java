package com.rodolpho.projetothais.service.impl;

import org.springframework.stereotype.Service;

import com.rodolpho.projetothais.entity.Nurse;
import com.rodolpho.projetothais.payload.NurseDto;
import com.rodolpho.projetothais.repository.NurseRepository;
import com.rodolpho.projetothais.service.NurseService;

@Service
public class NurseServiceImpl implements NurseService {

    NurseRepository nurseRepository;

    public NurseServiceImpl(NurseRepository nurseRepository){
        this.nurseRepository = nurseRepository;
    }

    @Override
    public NurseDto createNurse(NurseDto nurseDto) {

        Nurse nurse = new Nurse();

        nurse.setId(nurseDto.getId());
        nurse.setName(nurseDto.getName());
        nurse.setEmail(nurseDto.getEmail());
        nurse.setPhone(nurseDto.getName());
        nurse.setAge(nurseDto.getAge());
        nurse.setGender(nurseDto.getGender());
        nurse.setCity(nurseDto.getCity());
        nurse.setDescription(nurseDto.getDescription());
        nurse.setExperienceYears(nurseDto.getExperienceYears());
        nurse.setGraduation(nurseDto.getGraduation());

        Nurse newNurse = nurseRepository.save(nurse);

        NurseDto nurseResponse = new NurseDto();

        nurseResponse.setId(newNurse.getId());
        nurseResponse.setName(newNurse.getName());
        nurseResponse.setEmail(newNurse.getEmail());
        nurseResponse.setPhone(newNurse.getPhone());
        nurseResponse.setEmail(newNurse.getEmail());
        nurseResponse.setPhone(newNurse.getPhone());
        nurseResponse.setAge(newNurse.getAge());
        nurseResponse.setGender(newNurse.getGender());
        nurseResponse.setDescription(newNurse.getDescription());
        nurseResponse.setExperienceYears(newNurse.getExperienceYears());
        nurseResponse.setGraduation(newNurse.getGraduation());
        nurseResponse.setCity(newNurse.getCity());
        
        return nurseResponse;
    }
    
}
