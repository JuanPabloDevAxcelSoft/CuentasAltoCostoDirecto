package com.savia.hemofilia.service;

import com.savia.hemofilia.model.IllnesModel;
import com.savia.hemofilia.valueobject.Message;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EnfermedadesServiceDirect {
    void loadDataBase(String ruta, String tabla);

    IllnesModel tblIllness(Integer id);

    List<IllnesModel> allIllness();

    ResponseEntity<Message> loadDataBaseDirect(String ruta, Integer id);

}
