package com.savia.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savia.app.service.DetallePacienteService;
import com.savia.app.vo.ResponseMessage;

@RestController
@RequestMapping(value = "/api/v1")
public class DetallePacienteController {

    @Autowired
    private DetallePacienteService detallePacienteService;

    @GetMapping("/paciente")
    public ResponseEntity<ResponseMessage> getPaciente() {
        return this.detallePacienteService.getAllPacientePaginated();
    }

    // @GetMapping("/detalles/{id}")
    // public ResponseEntity<ResponseMessage> getDetallePaciente(@PathVariable("id")
    // Long id) {
    // return this.detallePacienteService.getDetallePaciente(id);
    // }
}
