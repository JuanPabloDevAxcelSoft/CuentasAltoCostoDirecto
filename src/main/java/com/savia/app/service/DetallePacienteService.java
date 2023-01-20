package com.savia.app.service;

import com.savia.app.dto.ListarPacienteDto;
import org.springframework.http.ResponseEntity;

import com.savia.app.vo.ResponseMessage;

public interface DetallePacienteService {
    public ResponseEntity<ResponseMessage> getAllPacientePaginated();

    public ResponseEntity<ResponseMessage> getDetallePaciente(ListarPacienteDto listarPacienteDto);

}
