package com.savia.app.service;

import com.savia.app.dto.ListarPacienteDto;
import org.springframework.http.ResponseEntity;

import com.savia.app.vo.ResponseMessage;
import com.savia.app.vo.ResponsePaciente;

public interface DetallePacienteService {
     ResponseEntity<ResponseMessage> getAllPacientePaginated();

     ResponseEntity<ResponsePaciente> getCmPaciente(ListarPacienteDto listarPacienteDto);

     ResponseEntity<ResponseMessage> getDetallePaciente(int idDetallePaciente);

}
