package com.savia.app.controller;

import com.savia.app.constants.EnumNombreColumnasTablaCmEnfermedad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.savia.app.service.EnfermedadesReadService;
import com.savia.app.service.EnfermedadesWriteService;
import com.savia.app.vo.ResponseMessage;

@RestController
@RequestMapping(path = "/api/v1")
public class EnfermedadesController {

    @Autowired
    private EnfermedadesReadService enfermedadesReadService;

    @Autowired
    private EnfermedadesWriteService enfermedadesWriteService;

    @GetMapping("/enfermedades")
    public ResponseEntity<ResponseMessage> getAllEnfermedad() {
        try {
            return enfermedadesReadService.getAllEnfermedades();
        } catch (Exception e) {
            return null;
        }
    }
    @GetMapping("/enfermedades/novedades/")
    public String  getNovedades(@RequestParam("idEnfermedad") int idEnfermedad) {
        try {
            return enfermedadesReadService.getNombreTablaGeneric(EnumNombreColumnasTablaCmEnfermedad.novedades.toString(),idEnfermedad);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/enfermedades/{idEnfermedad}")
    public ResponseEntity<ResponseMessage> getEnfermedad(@PathVariable("idEnfermedad") int idEnfermedad) {
        try {
            return enfermedadesReadService.tblIllness(idEnfermedad);
        } catch (Exception e) {
            return null;
        }
    }
    @GetMapping("/nombre/paso/{idEnfermedad}")
    public String getNombreTablaPaso(@PathVariable("idEnfermedad") int idEnfermedad) {
        try {
            return enfermedadesReadService.getNombreTablaGeneric(EnumNombreColumnasTablaCmEnfermedad.nombre_tabla_paso.toString(), idEnfermedad);
        } catch (Exception e) {
            return null;
        }
    }
    @GetMapping("/nombre/clase/validacion")
    public String getNombreClaseValidacion(@RequestParam("idEnfermedad") int idEnfermedad) {
        try {
            return enfermedadesReadService.getNombreTablaGeneric(EnumNombreColumnasTablaCmEnfermedad.nombre_clase_validacion.toString(),idEnfermedad);
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/enfermedades")
    public ResponseEntity<ResponseMessage> updateEnfermedad(
            @RequestParam("idEnfermedad") int idEnfermedad,
            @RequestParam("enfermedad") String nombreEnfermedad) {
        return enfermedadesWriteService.updateEnfermedad(idEnfermedad, nombreEnfermedad);
    }

    @DeleteMapping("/enfermedades/{idEnfermedad}")
    public ResponseEntity<ResponseMessage> updateIllness(@PathVariable("idEnfermedad") int idEnfermedad) {
        return enfermedadesWriteService.deleteEnfermedad(idEnfermedad);
    }
}
