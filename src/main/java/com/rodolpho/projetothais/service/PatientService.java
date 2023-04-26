package com.rodolpho.projetothais.service;

import com.rodolpho.projetothais.payload.PatientDto;

public interface PatientService {
    
    PatientDto createPatient(long NurseId, PatientDto patientDto);
}
