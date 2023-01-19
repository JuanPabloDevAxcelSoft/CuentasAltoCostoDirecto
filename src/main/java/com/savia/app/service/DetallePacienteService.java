package com.savia.app.service;

import org.springframework.http.ResponseEntity;

import com.savia.app.vo.ResponseMessage;

public interface DetallePacienteService {
    public ResponseEntity<ResponseMessage> getAllPacientePaginated();

    public ResponseEntity<ResponseMessage> getDetallePaciente(int idEnfermedad,int idIps, int limit,int page);

}
