package com.savia.app.service;

import org.springframework.http.ResponseEntity;

import com.savia.app.dto.EnfermedadesReadDto;
import com.savia.app.vo.ResponseMessage;

import java.util.List;

public interface EnfermedadesReadService {
    ResponseEntity<ResponseMessage> tblIllness(Integer id);

    ResponseEntity<ResponseMessage> getAllEnfermedades();

    EnfermedadesReadDto findEnfermedadById(Integer id);

    String getNombreTablaGeneric(String columna, Integer id);

    List<Integer> getAllId();
     List<Object> getCantidadValidar(String nombreTabla);

}
