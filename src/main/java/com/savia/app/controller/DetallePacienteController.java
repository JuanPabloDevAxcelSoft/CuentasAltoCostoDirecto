package com.savia.app.controller;

import com.savia.app.dto.ListarPacienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savia.app.service.DetallePacienteService;
import com.savia.app.vo.ResponsePaciente;

@RestController
@RequestMapping(value = "/api/v1")
public class DetallePacienteController {

    @Autowired
    private DetallePacienteService detallePacienteService;

    @PostMapping("/consulta/paciente")
    public ResponseEntity<ResponsePaciente> getPaciente(@RequestBody ListarPacienteDto listarPacienteDto) {
        return this.detallePacienteService.getDetallePaciente(listarPacienteDto);
    }

}
