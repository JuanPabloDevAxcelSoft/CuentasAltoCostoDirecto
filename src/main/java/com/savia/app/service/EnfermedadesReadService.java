package com.savia.app.service;

import org.springframework.http.ResponseEntity;

import com.savia.app.dto.EnfermedadesReadDto;

public interface EnfermedadesReadService {
    ResponseEntity<String> tblIllness(Integer id);

    ResponseEntity<String> allIllness();

    EnfermedadesReadDto findIllnessById(Integer id);

}
