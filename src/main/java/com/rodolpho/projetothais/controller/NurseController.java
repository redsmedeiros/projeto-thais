package com.rodolpho.projetothais.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private List<NurseDto> getAllNurses(
        @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
        @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){

        return nurseService.getAllNurses(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    private ResponseEntity<NurseDto> getNurseById(@PathVariable(name = "id") Long nurseId){

        NurseDto nurseResponse = nurseService.getSingleNurse(nurseId);

        return new ResponseEntity<>(nurseResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<NurseDto> updateNurse(@PathVariable(name = "id") Long nurseId, @RequestBody NurseDto nurseDto){

        NurseDto updatedNurse = nurseService.updateNurse(nurseId, nurseDto);
        
        return new ResponseEntity<>(updatedNurse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteNurse(@PathVariable(name = "id") Long nurseId){

        nurseService.deleteNurse(nurseId);

        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }
    
}
