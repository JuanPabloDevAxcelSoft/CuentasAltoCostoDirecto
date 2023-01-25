package com.savia.app.service;

import org.springframework.http.ResponseEntity;

import com.savia.app.dto.EnfermedadesReadDto;
import com.savia.app.vo.ResponseMessage;

public interface EnfermedadesReadService {
    ResponseEntity<ResponseMessage> tblIllness(Integer id);

    ResponseEntity<ResponseMessage> getAllEnfermedades();

    EnfermedadesReadDto findEnfermedadById(Integer id);

    /* True: consulta tablas finales, False: Tablas de de paso */
    public String getNombreTablaGeneric(boolean tabla, Integer id);

}
