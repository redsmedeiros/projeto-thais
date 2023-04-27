package com.rodolpho.projetothais.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rodolpho.projetothais.entity.Nurse;
import com.rodolpho.projetothais.entity.Patient;
import com.rodolpho.projetothais.exception.NurseAPIException;
import com.rodolpho.projetothais.exception.ResourceNotFoundException;
import com.rodolpho.projetothais.payload.PatientDto;
import com.rodolpho.projetothais.repository.NurseRepository;
import com.rodolpho.projetothais.repository.PatientRepository;
import com.rodolpho.projetothais.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

    private NurseRepository nurseRepository;

    private PatientRepository patientRepository;
   
    public PatientServiceImpl(NurseRepository nurseRepository, PatientRepository patientRepository){
        this.nurseRepository = nurseRepository;
        this.patientRepository = patientRepository;
    }
       

    @Override
    public PatientDto createPatient(long nurseId, PatientDto patientDto) {

        Patient patient = mapToEntity(patientDto);

        //recuperar enfermeira
        Nurse nurse = nurseRepository.findById(nurseId).orElseThrow(()-> new ResourceNotFoundException("nurseId", "nurseId", nurseId));

        //colocar a enfermeira encontrada no paciente
        patient.setNurse(nurse);

        //salvar
        Patient newPatient = patientRepository.save(patient);
       
        return mapToDto(newPatient);
    }

    @Override
    public List<PatientDto> getPatientByNurseId(long nurseId) {

        List<Patient> patients = patientRepository.findByNurseId(nurseId);
        
        return patients.stream().map(patient -> mapToDto(patient)).collect(Collectors.toList());
    }

    @Override
    public PatientDto getPatientByid(long nurseId, long patientId){

        Nurse nurse = nurseRepository.findById(nurseId).orElseThrow(()-> new ResourceNotFoundException("nurseId", "nurseId", nurseId));
       
        Patient patient = patientRepository.findById(patientId).orElseThrow(()-> new ResourceNotFoundException("patientId", "id", patientId));

        //se pertencer a enfermeira pode retornar
        if(!patient.getNurse().getId().equals(nurse.getId())){
            throw new NurseAPIException(HttpStatus.BAD_REQUEST, "Patient does not belong to nurse");
        }

        return mapToDto(patient);
    }

    @Override
    public PatientDto updatePatientById(long nurseId, long patientId, PatientDto patientDto){

        Nurse nurse = nurseRepository.findById(nurseId).orElseThrow(()-> new ResourceNotFoundException("nurseid", "id", nurseId));

        Patient patient = patientRepository.findById(patientId).orElseThrow(()-> new ResourceNotFoundException("patientId", "id", patientId));

        if(!patient.getNurse().getId().equals(nurse.getId())){
            throw new NurseAPIException(HttpStatus.BAD_REQUEST, "Patient does not belong to nurse");
        }

        Patient existingPatient = patientRepository.findByEmail(patientDto.getEmail());

        if (existingPatient != null && existingPatient.getId() != patientId) {
            throw new NurseAPIException(HttpStatus.BAD_REQUEST, "Email already in use");
        }

        patient.setEmail(patientDto.getEmail());
        patient.setName(patientDto.getName());
        patient.setGender(patientDto.getGender());
        patient.setAge(patientDto.getAge());
        patient.setCity(patientDto.getCity());
        patient.setPhone(patientDto.getPhone());

        Patient updatePatient = patientRepository.save(patient);
       
        return mapToDto(updatePatient);
    }

    @Override
    public void deletePatientById(long nurseId, long patientId) {
        
        Nurse nurse = nurseRepository.findById(nurseId).orElseThrow(()-> new ResourceNotFoundException("nurseId", "id", nurseId));

        Patient patient = patientRepository.findById(patientId).orElseThrow(()-> new ResourceNotFoundException("nurseId", "id", patientId));

        if(!patient.getNurse().getId().equals(nurse.getId())){
            throw new NurseAPIException(HttpStatus.BAD_REQUEST, "Patient does not belong to nurse");
        }

        patientRepository.delete(patient);
    }

    private PatientDto mapToDto(Patient patient){

        PatientDto patientDto = new PatientDto();

        patientDto.setId(patient.getId());
        patientDto.setName(patient.getName());
        patientDto.setEmail(patient.getEmail());
        patientDto.setPhone(patient.getPhone());
        patientDto.setAge(patient.getAge());
        patientDto.setCity(patient.getCity());
        patientDto.setGender(patient.getGender());
        
        return patientDto;

    }

    private Patient mapToEntity(PatientDto patientDto){

        Patient patient = new Patient();

        patient.setId(patientDto.getId());
        patient.setName(patientDto.getName());
        patient.setEmail(patientDto.getEmail());
        patient.setPhone(patientDto.getPhone());
        patient.setAge(patientDto.getAge());
        patient.setCity(patientDto.getCity());
        patient.setGender(patientDto.getGender());

        return patient;
    }


    


    


   


    


    
}
