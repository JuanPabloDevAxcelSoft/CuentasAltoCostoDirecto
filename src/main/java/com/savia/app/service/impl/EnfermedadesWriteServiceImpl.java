package com.savia.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.savia.app.model.CmEnfermedades;
import com.savia.app.repository.EnfermedadesWriteRepository;
import com.savia.app.service.EnfermedadesWriteService;
import com.savia.app.vo.ResponseMessage;

@Service
public class EnfermedadesWriteServiceImpl implements EnfermedadesWriteService {
    @Autowired
    EnfermedadesWriteRepository enfermedadesRepository;

    @Override
    public ResponseEntity<ResponseMessage> deleteEnfermedad(int idEnfermedad) {
        ResponseMessage response = new ResponseMessage();
        try {
            if (idEnfermedad > 0) {
                enfermedadesRepository.deleteById(idEnfermedad);
            }
            response.setMessage((idEnfermedad > 0) ? "Se elimino la enfermedad correctamente"
                    : "El id no es aceptado para realizar la eliminación");
            response.setStatus((idEnfermedad > 0) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setMessage("No se elimino la enfermedad  : " + e.getLocalizedMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<ResponseMessage> updateEnfermedad(int idEnfermedad, String nombreEnfermedad) {
        ResponseMessage response = new ResponseMessage();
        try {
            if (!nombreEnfermedad.isEmpty()) {
                String nombreTabla = "cm_" + nombreEnfermedad.toLowerCase() + "_paso";
                enfermedadesRepository
                        .save(new CmEnfermedades(idEnfermedad, nombreTabla, nombreEnfermedad.toLowerCase()));
            }
            response.setMessage((!nombreEnfermedad.isEmpty()) ? "Se cargo la enfermedad correctamente"
                    : "El nombre de la enfermedad no puede ser vacio");
            response.setStatus((!nombreEnfermedad.isEmpty()) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setMessage("No se cargo la enfermedad  : " + e.getLocalizedMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<ResponseMessage> saveEnfermedad(String nombreEnfermedad) {
        ResponseMessage response = new ResponseMessage();
        try {
            if (!nombreEnfermedad.isEmpty()) {
                String nombreTabla = "cm_" + nombreEnfermedad.toLowerCase() + "_paso";
                enfermedadesRepository.save(new CmEnfermedades(nombreTabla, nombreEnfermedad.toLowerCase()));
            }
            response.setMessage((!nombreEnfermedad.isEmpty()) ? "Se cargo la enfermedad correctamente"
                    : "El nombre de la enfermedad  no puede ser vacio");
            response.setStatus((!nombreEnfermedad.isEmpty()) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setMessage("No se cargo la enfermedad  : " + e.getLocalizedMessage());
        }
        return ResponseEntity.ok().body(response);
    }
}
