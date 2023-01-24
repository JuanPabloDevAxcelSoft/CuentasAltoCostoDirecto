package com.savia.app.service;

import com.savia.app.dto.ListarPacienteDto;
import org.springframework.http.ResponseEntity;

import com.savia.app.vo.ResponseMessage;
import com.savia.app.vo.ResponsePaciente;

public interface DetallePacienteService {
    public ResponseEntity<ResponseMessage> getAllPacientePaginated();

    public ResponseEntity<ResponsePaciente> getDetallePaciente(ListarPacienteDto listarPacienteDto);

}
