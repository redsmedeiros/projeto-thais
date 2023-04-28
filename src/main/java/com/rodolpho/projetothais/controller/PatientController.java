package com.rodolpho.projetothais.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodolpho.projetothais.payload.PatientDto;
import com.rodolpho.projetothais.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PatientController {

    private PatientService patientService;


    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @PostMapping("/nurses/{nurseId}/patients")
    public ResponseEntity<PatientDto> createPatient(@PathVariable(value = "nurseId") long nurseId, @Valid @RequestBody PatientDto patientDto){

        PatientDto patientResponse = patientService.createPatient(nurseId, patientDto);

        return new ResponseEntity<>(patientResponse, HttpStatus.CREATED);
    }

    @GetMapping("/nurses/{nurseId}/patients")
    public List<PatientDto> getAllPatientsByNurseId(@PathVariable(value = "nurseId") long nurseId){

        List<PatientDto> patients = patientService.getPatientByNurseId(nurseId);

        return patients;
    }

    @GetMapping("/nurses/{nurseId}/patients/{patientId}")
    public ResponseEntity<PatientDto> getpatientById(@PathVariable(value = "nurseId") long nurseId, @PathVariable(value = "patientId")  long patientId){

        PatientDto patientResponse = patientService.getPatientByid(nurseId, patientId);

        return new ResponseEntity<PatientDto>(patientResponse, HttpStatus.OK);
    }

    @PatchMapping("/nurses/{nurseId}/patients/{patientId}")
    public ResponseEntity<PatientDto> updatePatientById(@PathVariable(value = "nurseId") long nurseId, @PathVariable(value = "patientId") long patientId, @Valid @RequestBody PatientDto patientDto){

        PatientDto updatePatient = patientService.updatePatientById(nurseId, patientId, patientDto);

        return new ResponseEntity<>(updatePatient, HttpStatus.OK);
    }


    @DeleteMapping("/nurses/{nurseId}/patients/{patientId}")
    public ResponseEntity<String> deletePatientId(@PathVariable(value = "nurseId") long nurseId, @PathVariable(value = "patientId") long patientId){

        patientService.deletePatientById(nurseId, patientId);

        return new ResponseEntity<String>("Patient deleted successfully", HttpStatus.OK);
    }


}
