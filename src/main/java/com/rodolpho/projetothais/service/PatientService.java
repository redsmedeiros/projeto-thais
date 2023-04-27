package com.rodolpho.projetothais.service;

import java.util.List;

import com.rodolpho.projetothais.payload.PatientDto;

public interface PatientService {
    
    PatientDto createPatient(long NurseId, PatientDto patientDto);

    List<PatientDto> getPatientByNurseId(long nurseId);

    PatientDto getPatientByid(long nurseId, long patientId);

    PatientDto updatePatientById(long nurseId, long patientId, PatientDto patientDto);
}
