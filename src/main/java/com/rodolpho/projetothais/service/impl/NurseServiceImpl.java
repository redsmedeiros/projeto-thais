package com.rodolpho.projetothais.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rodolpho.projetothais.entity.Nurse;
import com.rodolpho.projetothais.exception.ResourceNotFoundException;
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

        Nurse nurse = mapToEntity(nurseDto); 

        Nurse newNurse = nurseRepository.save(nurse);

        NurseDto nurseResponse = mapToDto(newNurse);

        return nurseResponse;
    }

    @Override
    public List<NurseDto> getAllNurses() {
       
        List<Nurse> nurses = nurseRepository.findAll();

        List<NurseDto> nursesDto = nurses.stream().map(nurse -> mapToDto(nurse)).collect(Collectors.toList());

        return nursesDto;
    }

    @Override
    public NurseDto getSingleNurse(Long nurseId) {
        
        Nurse nurse = nurseRepository.findById(nurseId).orElseThrow(()-> new ResourceNotFoundException("nurse", "NurseId", nurseId));

        return mapToDto(nurse);
    }


    private NurseDto mapToDto(Nurse nurse){

        NurseDto nurseResponse = new NurseDto();

        nurseResponse.setId(nurse.getId());
        nurseResponse.setName(nurse.getName());
        nurseResponse.setEmail(nurse.getEmail());
        nurseResponse.setPhone(nurse.getPhone());
        nurseResponse.setEmail(nurse.getEmail());
        nurseResponse.setPhone(nurse.getPhone());
        nurseResponse.setAge(nurse.getAge());
        nurseResponse.setGender(nurse.getGender());
        nurseResponse.setDescription(nurse.getDescription());
        nurseResponse.setExperienceYears(nurse.getExperienceYears());
        nurseResponse.setGraduation(nurse.getGraduation());
        nurseResponse.setCity(nurse.getCity());

        return nurseResponse;
    }

    private Nurse mapToEntity(NurseDto nurseDto){

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

        return nurse;

    }

    
    
}
