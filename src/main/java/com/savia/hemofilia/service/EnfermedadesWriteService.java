package com.savia.hemofilia.service;

import com.savia.hemofilia.valueobject.Message;
import org.springframework.http.ResponseEntity;

public interface EnfermedadesWriteService {
    ResponseEntity<Message> saveEnfermedad(String nombreEnfermedad);

    ResponseEntity<Message> updateEnfermedad(int idEnfermedad, String nombreEnfermedad);

    ResponseEntity<Message> deleteEnfermedad(int idEnfermedad);

}
