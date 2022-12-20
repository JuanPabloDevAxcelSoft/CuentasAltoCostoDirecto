package com.savia.app.service;

import org.springframework.http.ResponseEntity;

import com.savia.app.vo.ResponseMessage;

public interface DetallePacienteService {
    public ResponseEntity<ResponseMessage> getAllPacientePaginated();

    public ResponseEntity<ResponseMessage> getDetallePaciente(Long id);

}
