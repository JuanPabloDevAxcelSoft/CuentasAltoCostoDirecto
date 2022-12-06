package com.savia.hemofilia.service.impl;

import com.savia.hemofilia.service.CargaDirectaService;
import com.savia.hemofilia.service.EnfermedadesReadService;
import com.savia.hemofilia.valueobject.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public ResponseEntity<Message> loadDataBaseDirect(String ruta, Integer id) {
        try {
            String s = "COPY " + enfermedadesServiceDirect.tblIllness(id).getNameTables() + " from '" + ruta
                    + "' with DELIMITER ';' CSV HEADER;";
            Query nativeQuery = entityManager.createNativeQuery(s);
            nativeQuery.executeUpdate();
            return ResponseEntity.ok()
                    .body(new Message("El archivo se cargo correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new Message("El archivo: tiene un error  : " + e.getLocalizedMessage()));
        }
    }
    @Override
    @Transactional
    public void loadDataBase(String ruta, String tabla) {
        String s = "COPY " + tabla + " from '" + ruta + "' with DELIMITER ';' CSV HEADER;";
        Query nativeQuery = entityManager.createNativeQuery(s);
        nativeQuery.executeUpdate();
    }
}
