package com.savia.app.service;

import org.springframework.http.ResponseEntity;

import com.savia.app.valueobject.Message;

public interface CargaDirectaService {
    ResponseEntity<Message> loadDataBaseDirect(String ruta, Integer id);

    void loadDataBase(String ruta, String tabla);
}
