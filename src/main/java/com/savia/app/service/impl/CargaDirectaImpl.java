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
import com.savia.app.util.ResponseEntityJson;

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
    public ResponseEntity<String> loadDataBaseDirect(String ruta, Integer id) {
        ResponseEntityJson jsonResponse = new ResponseEntityJson();
        String message = "";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            if ((!ruta.isEmpty()) && (id > 0)) {
                EnfermedadesReadDto enfermedadesReadDtoObj = enfermedadesServiceDirect.findIllnessById(id);
                if (enfermedadesReadDtoObj != null) {
                    String pureSql = "COPY " + enfermedadesReadDtoObj.getNameTables() + " from '" + ruta
                            + "' with DELIMITER ';' CSV HEADER;";
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
        return jsonResponse.ResponseHttp(message, status, null);
    }

    @Override
    @Transactional
    public void loadDataBase(String ruta, String tabla) {
        String pureSql = "COPY " + tabla + " from '" + ruta + "' with DELIMITER ';' CSV HEADER;";
        Query nativeQuery = entityManager.createNativeQuery(pureSql);
        nativeQuery.executeUpdate();
    }
}
