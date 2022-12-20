package com.savia.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.savia.app.dto.EnfermedadesReadDto;
import com.savia.app.service.CargaDirectaService;
import com.savia.app.service.EnfermedadesReadService;
import com.savia.app.vo.ResponseMessage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class CargaDirectaImpl implements CargaDirectaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    EnfermedadesReadService enfermedadesServiceDirect;

    @Override
    @Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
    public ResponseEntity<ResponseMessage> loadDataBaseDirect(String ruta, Integer id) {
        ResponseMessage response = new ResponseMessage();
        String message = "";
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            if ((!ruta.isEmpty()) && (id > 0)) {
                EnfermedadesReadDto enfermedadesReadDtoObj = enfermedadesServiceDirect.findIllnessById(id);
                if (enfermedadesReadDtoObj != null) {
                    String pureSql = "LOAD DATA LOCAL INFILE '" + ruta +
                            "' INTO TABLE " + enfermedadesReadDtoObj.getNameTables()
                            + " FIELDS TERMINATED BY ';' LINES TERMINATED BY '\\n' IGNORE 1 ROWS;";
                    Query nativeQuery = entityManager.createNativeQuery(pureSql);
                    nativeQuery.executeUpdate();
                    status = HttpStatus.OK;
                    message = "El archivo fue cargado correctamente";
                } else {
                    message = "Id de la enfermedad no existe";
                    status = HttpStatus.NOT_FOUND;
                }
            } else {
                message = "Verifique los datos al momento de enviar, Datos no aceptados.";
                status = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception e) {
            message = "El archivo: tiene un error : " + e.getLocalizedMessage();
        }
        response.setMessage(message);
        response.setStatus(status);
        return ResponseEntity.ok().body(response);
    }
}
