package com.rodolpho.projetothais.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodolpho.projetothais.payload.NurseDto;
import com.rodolpho.projetothais.service.NurseService;

@RestController
@RequestMapping("/api/nurse")
public class NurseController {

    NurseService nurseService;

    public NurseController(NurseService nurseService){
        this.nurseService = nurseService;
    }

    @PostMapping
    private ResponseEntity<NurseDto> createNurse(@RequestBody NurseDto nurseDto){

        NurseDto nurseResponse = nurseService.createNurse(nurseDto);

        return new ResponseEntity<>(nurseResponse, HttpStatus.CREATED);
    }

    @GetMapping
    private List<NurseDto> getAllNurses(){

        return nurseService.getAllNurses();
    }

    @GetMapping("/{id}")
    private ResponseEntity<NurseDto> getNurseById(@PathVariable(name = "id") Long nurseId){

        NurseDto nurseResponse = nurseService.getSingleNurse(nurseId);

        return new ResponseEntity<>(nurseResponse, HttpStatus.OK);
    }
    
}
