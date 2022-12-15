package com.savia.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.savia.app.model.EnfermedadesWriteModel;
import com.savia.app.repository.EnfermedadesWriteRepository;
import com.savia.app.service.EnfermedadesWriteService;
import com.savia.app.util.ResponseEntityJson;
import java.util.Date;

@Service
public class EnfermedadesWriteServiceImpl implements EnfermedadesWriteService {
    @Autowired
    EnfermedadesWriteRepository enfermedadesRepository;

    @Override
    public ResponseEntity<String> deleteEnfermedad(int idEnfermedad) {
        ResponseEntityJson jsonResponse = new ResponseEntityJson();
        String message = "";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            if (idEnfermedad > 0) {
                enfermedadesRepository.deleteById(idEnfermedad);
            }
            status = (idEnfermedad > 0) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            message = (idEnfermedad > 0) ? "Se elimino la enfermedad correctamente"
                    : "El id no es aceptado para realizar la eliminación";
        } catch (Exception e) {
            message = "No se elimino la enfermedad  : " + e.getLocalizedMessage();
        }
        return jsonResponse.ResponseHttp(message, status, null);
    }

    @Override
    public ResponseEntity<String> updateEnfermedad(int idEnfermedad, String nombreEnfermedad) {
        ResponseEntityJson jsonResponse = new ResponseEntityJson();
        String message = "";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            if (!nombreEnfermedad.isEmpty()) {
                enfermedadesRepository.save(new EnfermedadesWriteModel(idEnfermedad,
                        "tbl_" + nombreEnfermedad.toLowerCase() + "_paso", new Date(), true));
            }
            message = (!nombreEnfermedad.isEmpty()) ? "Se cargo la enfermedad correctamente"
                    : "El nombre de la enfermedad no puede ser vacio";
            status = (!nombreEnfermedad.isEmpty()) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            message = "No se cargo la enfermedad  : " + e.getLocalizedMessage();
        }
        return jsonResponse.ResponseHttp(message, status, null);
    }

    @Override
    public ResponseEntity<String> saveEnfermedad(String nombreEnfermedad) {
        ResponseEntityJson jsonResponse = new ResponseEntityJson();
        String message = "";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            if (!nombreEnfermedad.isEmpty()) {
                enfermedadesRepository.save(
                        new EnfermedadesWriteModel("tbl_" + nombreEnfermedad.toLowerCase() + "_paso",
                                nombreEnfermedad.toLowerCase(), new Date(), true));
            }
            message = (!nombreEnfermedad.isEmpty()) ? "Se cargo la enfermedad correctamente"
                    : "El nombre de la enfermedad  no puede ser vacio";
            status = (!nombreEnfermedad.isEmpty()) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            message = "No se cargo la enfermedad  : " + e.getLocalizedMessage();
        }
        return jsonResponse.ResponseHttp(message, status, null);
    }

}
