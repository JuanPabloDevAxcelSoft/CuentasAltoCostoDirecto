package com.savia.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    public ResponseEntity<ResponseMessage> getPaciente(@Param("idEnfermedad") int idEnfermedad) {
        return this.detallePacienteService.getDetallePaciente(idEnfermedad);
    }

}
