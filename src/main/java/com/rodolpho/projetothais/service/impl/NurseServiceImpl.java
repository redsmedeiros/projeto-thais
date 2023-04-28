package com.rodolpho.projetothais.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rodolpho.projetothais.entity.Nurse;
import com.rodolpho.projetothais.exception.ResourceNotFoundException;
import com.rodolpho.projetothais.payload.NurseDto;
import com.rodolpho.projetothais.payload.NurseResponse;
import com.rodolpho.projetothais.repository.NurseRepository;
import com.rodolpho.projetothais.service.NurseService;

@Service
public class NurseServiceImpl implements NurseService {

    NurseRepository nurseRepository;

    private ModelMapper modelMapper;

    public NurseServiceImpl(NurseRepository nurseRepository, ModelMapper modelMapper){
        this.nurseRepository = nurseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public NurseDto createNurse(NurseDto nurseDto) {

        Nurse nurse = mapToEntity(nurseDto); 

        Nurse newNurse = nurseRepository.save(nurse);

        NurseDto nurseResponse = mapToDto(newNurse);

        return nurseResponse;
    }

    @Override
    public NurseResponse getAllNurses(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);
       
        Page<Nurse> nurses = nurseRepository.findAll(pageable);

        List<Nurse> listOfNurses = nurses.getContent();

        List<NurseDto> content = listOfNurses.stream().map(nurse -> mapToDto(nurse)).collect(Collectors.toList());

        NurseResponse nurseResponse = new NurseResponse();

        Long totalNursesWithExperienceGreaterThanThreshold = nurseRepository.countNursesWithExperienceGreaterThan(10);

        List<Nurse> genderFeminino = nurseRepository.findAllFemaleNurses();

        int qtdFeminino = genderFeminino.size();

        List<Nurse> genderMasculino = nurseRepository.findAllMaleNurses();

        int qtdMasculino = genderMasculino.size();

        Double mediaFeminino = nurseRepository.findAverageFemaleNurses();

        nurseResponse.setContent(content);
        nurseResponse.setPageNo(nurses.getNumber());
        nurseResponse.setPageSize(nurses.getSize());
        nurseResponse.setTotalElement(nurses.getTotalElements());
        nurseResponse.setTotalElement(nurses.getTotalPages());
        nurseResponse.setLast(nurses.isLast());
        nurseResponse.setMore10Years(totalNursesWithExperienceGreaterThanThreshold);
        nurseResponse.setGenderFeminino(qtdFeminino);
        nurseResponse.setGenderMasculino(qtdMasculino);
        nurseResponse.setMediaFeminina(mediaFeminino);

        return nurseResponse;
    }

    @Override
    public NurseDto getSingleNurse(Long nurseId) {
        
        Nurse nurse = nurseRepository.findById(nurseId).orElseThrow(()-> new ResourceNotFoundException("nurse", "NurseId", nurseId));

        return mapToDto(nurse);
    }

    @Override
    public void deleteNurse(Long nurseId) {
        
        Nurse nurse = nurseRepository.findById(nurseId).orElseThrow(()-> new ResourceNotFoundException("nurse", "NurseId", nurseId));

        nurseRepository.delete(nurse);
    }

    @Override
    public NurseDto updateNurse(Long nurseId, NurseDto nurseDto) {
        
        Nurse nurse = nurseRepository.findById(nurseId).orElseThrow(()-> new ResourceNotFoundException("nurse", "NurseId", nurseId));
        
        nurse.setName(nurseDto.getName());
        nurse.setEmail(nurseDto.getEmail());
        nurse.setAge(nurseDto.getAge());
        nurse.setCity(nurseDto.getCity());
        nurse.setDescription(nurseDto.getDescription());
        nurse.setGender(nurseDto.getGender());
        nurse.setPhone(nurseDto.getPhone());
        nurse.setGraduation(nurseDto.getGraduation());
        nurse.setExperienceYears(nurseDto.getExperienceYears());

        Nurse updateNurse = nurseRepository.save(nurse);
        
        return mapToDto(updateNurse);
    }


    private NurseDto mapToDto(Nurse nurse){

        NurseDto nurseDto = modelMapper.map(nurse, NurseDto.class);

        return nurseDto;
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
