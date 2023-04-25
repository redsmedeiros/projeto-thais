package com.rodolpho.projetothais.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodolpho.projetothais.payload.NurseDto;
import com.rodolpho.projetothais.service.NurseService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    NurseService nurseService;

    public PostController(NurseService nurseService){
        this.nurseService = nurseService;
    }

    @PostMapping
    private ResponseEntity<NurseDto> createNurse(@RequestBody NurseDto nurseDto){

        NurseDto nurseResponse = nurseService.createNurse(nurseDto);

        return new ResponseEntity<>(nurseResponse, HttpStatus.CREATED);
    }
    
}
