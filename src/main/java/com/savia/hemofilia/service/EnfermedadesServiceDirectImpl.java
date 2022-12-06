package com.savia.hemofilia.service;

import com.savia.hemofilia.interfaces.EnfermedadesServiceDirect;
import com.savia.hemofilia.model.IllnesModel;
import com.savia.hemofilia.repository.IllnesRepository;
import com.savia.hemofilia.valueobject.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class EnfermedadesServiceDirectImpl implements EnfermedadesServiceDirect {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    IllnesRepository illnesRepository;

    @Override
    public List<IllnesModel> allIllness() {
        return illnesRepository.findAll();
    }

    @Override
    public IllnesModel tblIllness(Integer id) {
        return illnesRepository.getById(id);
    }

    @Override
    @Transactional
    public void loadDataBase(String ruta, String tabla) {
        String s = "COPY " + tabla + " from '" + ruta + "' with DELIMITER ';' CSV HEADER;";
        Query nativeQuery = entityManager.createNativeQuery(s);
        nativeQuery.executeUpdate();
    }

    @Override
    @Transactional
    public ResponseEntity<Message> loadDataBaseDirect(String ruta, Integer id) {
        try {
            String s = "COPY " + illnesRepository.getById(id).getNameTables() + " from '" + ruta
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

}
