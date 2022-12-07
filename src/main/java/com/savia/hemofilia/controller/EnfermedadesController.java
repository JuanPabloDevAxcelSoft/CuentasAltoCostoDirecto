package com.savia.hemofilia.controller;

import com.savia.hemofilia.dto.EnfermedadesReadDto;
import com.savia.hemofilia.model.EnfermedadesReadModel;
import com.savia.hemofilia.service.EnfermedadesWriteService;
import com.savia.hemofilia.valueobject.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.savia.hemofilia.service.EnfermedadesReadService;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EnfermedadesController {

    @Autowired
    private EnfermedadesReadService enfermedadesReadService;
    @Autowired
    private EnfermedadesWriteService enfermedadesWriteService;

    @GetMapping("/enfermedades")
    public List<EnfermedadesReadModel> getAllEnfermedad() {
        try {
            return enfermedadesReadService.allIllness();
        } catch (Exception e) {
            return null;
        }
    }
    @GetMapping("/enfermedades/{idEnfermedad}")
    public EnfermedadesReadDto getEnfermedad(@PathVariable("idEnfermedad") int idEnfermedad) {
        try {
            return enfermedadesReadService.tblIllness(idEnfermedad);
        } catch (Exception e) {
            return null;
        }
    }
    @PutMapping("/enfermedades")
    public ResponseEntity<Message> updateEnfermedad(@RequestParam("idEnfermedad")int idEnfermedad,@RequestParam("enfermedad")String nombreEnfermedad){
        return enfermedadesWriteService.updateEnfermedad(idEnfermedad,nombreEnfermedad);
    }
    @PostMapping("/enfermedades")
    public ResponseEntity<Message> saveEnfermedad(@RequestParam("enfermedad") String nombreEnfermedad){
        return enfermedadesWriteService.saveEnfermedad(nombreEnfermedad);
    }
    @DeleteMapping("/enfermedades/{idEnfermedad}")
    public ResponseEntity<Message> updateIllness(@PathVariable("idEnfermedad") int idEnfermedad){
        return enfermedadesWriteService.deleteEnfermedad(idEnfermedad);
    }

}
