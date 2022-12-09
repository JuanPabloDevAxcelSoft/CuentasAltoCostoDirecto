package com.savia.app.service;

import org.springframework.http.ResponseEntity;

public interface EnfermedadesWriteService {
    ResponseEntity<String> saveEnfermedad(String nombreEnfermedad);

    ResponseEntity<String> updateEnfermedad(int idEnfermedad, String nombreEnfermedad);

    ResponseEntity<String> deleteEnfermedad(int idEnfermedad);

}
