package com.savia.app.service;

import org.springframework.http.ResponseEntity;

import com.savia.app.vo.ResponseMessage;

public interface EnfermedadesWriteService {
    /*ResponseEntity<ResponseMessage> saveEnfermedad(String nombreEnfermedad);*/

    ResponseEntity<ResponseMessage> updateEnfermedad(int idEnfermedad, String nombreEnfermedad);

    ResponseEntity<ResponseMessage> deleteEnfermedad(int idEnfermedad);

}
