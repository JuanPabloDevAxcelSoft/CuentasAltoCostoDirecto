package com.savia.app.service;

import com.savia.app.dto.ListarPacienteDto;
import com.savia.app.model.CmDetallePaciente;
import org.springframework.http.ResponseEntity;

import com.savia.app.vo.ResponseMessage;
import com.savia.app.vo.ResponsePaciente;

public interface DetallePacienteService {
     ResponseEntity<ResponseMessage> getAllPacientePaginated();
     ResponseEntity<ResponsePaciente> getLogErrores(ListarPacienteDto listarPacienteDto);

     ResponseEntity<ResponsePaciente> getCmPaciente(ListarPacienteDto listarPacienteDto);

     CmDetallePaciente  getDetallePacienteById(int idDetallePaciente);

}
