package com.savia.app.service;

import org.springframework.http.ResponseEntity;

public interface CargaDirectaService {
    public void loadDataBase(String ruta, String tabla);

    public ResponseEntity<String> loadDataBaseDirect(String ruta, Integer id);

}
