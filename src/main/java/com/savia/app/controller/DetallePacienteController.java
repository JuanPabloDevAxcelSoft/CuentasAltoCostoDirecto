package com.savia.app.controller;

import com.savia.app.dto.ListarPacienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.PathVariable;

import com.savia.app.service.DetallePacienteService;
import com.savia.app.vo.ResponseMessage;

@RestController
@RequestMapping(value = "/api/v1")
public class DetallePacienteController {

    @Autowired
    private DetallePacienteService detallePacienteService;

    @GetMapping("/consulta/paciente")
    public ResponseEntity<ResponseMessage> getPaciente(@RequestBody ListarPacienteDto listarPacienteDto) {
        return this.detallePacienteService.getCmPaciente(listarPacienteDto);
    }
    @GetMapping("/consulta/detalle/paciente")
    public  ResponseEntity<ResponseMessage> getDetallePaciente(@RequestParam int idDetallePaciente){
        return  this.detallePacienteService.getDetallePaciente(idDetallePaciente);
    }

}
