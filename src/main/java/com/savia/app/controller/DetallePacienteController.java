package com.savia.app.controller;

import com.savia.app.dto.ListarPacienteDto;
import com.savia.app.model.CmDetallePaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.savia.app.service.DetallePacienteService;
import com.savia.app.vo.ResponseMessage;
import com.savia.app.vo.ResponsePaciente;

@RestController
@RequestMapping(value = "/api/v1")
public class DetallePacienteController {

    @Autowired
    private DetallePacienteService detallePacienteService;

    @PostMapping("/consulta/paciente")
    public ResponseEntity<ResponsePaciente> getPaciente(@RequestBody ListarPacienteDto listarPacienteDto) {
        return this.detallePacienteService.getCmPaciente(listarPacienteDto);
    }
    @PostMapping("/consulta/log/errores")
    public ResponseEntity<ResponsePaciente> getErrores(@RequestBody ListarPacienteDto listarPacienteDto){
        return this.detallePacienteService.getLogErrores(listarPacienteDto);
    }
    @GetMapping("/consulta/detalle/paciente")
    public CmDetallePaciente getDetallePaciente(@RequestParam int idDetallePaciente){
        return  this.detallePacienteService.getDetallePacienteById(idDetallePaciente);
    }
}
