package com.rodolpho.projetothais.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodolpho.projetothais.payload.PatientDto;
import com.rodolpho.projetothais.service.PatientService;

@RestController
@RequestMapping("/api/")
public class PatientController {

    private PatientService patientService;


    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @PostMapping("/nurses/{nurseId}/patients")
    public ResponseEntity<PatientDto> createPatient(@PathVariable(value = "nurseId") long nurseId, @RequestBody PatientDto patientDto){

        PatientDto patientResponse = patientService.createPatient(nurseId, patientDto);

        return new ResponseEntity<>(patientResponse, HttpStatus.CREATED);
    }
}
