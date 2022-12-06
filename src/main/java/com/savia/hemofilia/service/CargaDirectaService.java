package com.savia.hemofilia.service;

import com.savia.hemofilia.valueobject.Message;
import org.springframework.http.ResponseEntity;

public interface CargaDirectaService {
    ResponseEntity<Message> loadDataBaseDirect(String ruta, Integer id);
    void loadDataBase(String ruta, String tabla);
}
