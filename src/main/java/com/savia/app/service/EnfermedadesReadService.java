package com.savia.app.service;

import org.springframework.http.ResponseEntity;

import com.savia.app.dto.EnfermedadesReadDto;
import com.savia.app.vo.ResponseMessage;

public interface EnfermedadesReadService {
    ResponseEntity<ResponseMessage> tblIllness(Integer id);
    ResponseEntity<ResponseMessage> allIllness();
    EnfermedadesReadDto findIllnessById(Integer id);
    String nomTabFin(Integer id);
    String nomTabPaso(Integer id);
    String nomClaseValidacion(Integer id);

}
