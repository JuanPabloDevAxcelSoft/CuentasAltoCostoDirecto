package com.savia.app.service;

import org.springframework.http.ResponseEntity;

import com.savia.app.vo.ResponseMessage;

public interface CargaDirectaService {
    public ResponseEntity<ResponseMessage> loadDataBaseDirect(String ruta, Integer id);

    // public void loadDataBase(String ruta, String tabla);
}
