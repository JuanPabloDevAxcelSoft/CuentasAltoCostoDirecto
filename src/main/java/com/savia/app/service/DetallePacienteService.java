package com.savia.app.service;

import com.savia.app.dto.ListarPacienteDto;
import org.springframework.http.ResponseEntity;

import com.savia.app.vo.ResponseJson;
import com.savia.app.vo.ResponseMessage;
import com.savia.app.vo.ResponsePaciente;

public interface DetallePacienteService {

     ResponseEntity<ResponseJson> getLogErrores(ListarPacienteDto listarPacienteDto);

     ResponseEntity<ResponsePaciente> getCmPaciente(ListarPacienteDto listarPacienteDto);

     ResponseEntity<ResponseMessage> getDetallePacienteById(int idDetallePaciente);

}
