package com.savia.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.savia.app.dto.EnfermedadesReadDto;
import com.savia.app.model.EnfermedadesReadModel;
import com.savia.app.repository.EnfermedadesReadRepository;
import com.savia.app.service.EnfermedadesReadService;
import com.savia.app.util.ResponseEntityJson;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnfermedadesReadServiceImpl implements EnfermedadesReadService {
    @Autowired
    EnfermedadesReadRepository enfermedadesRepository;

    @Override
    public ResponseEntity<String> allIllness() {
        ResponseEntityJson jsonResponse = new ResponseEntityJson();
        String message = "";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        List<EnfermedadesReadModel> list = new ArrayList<>();
        try {
            list = enfermedadesRepository.findAllByEstado(true);
            status = (!list.isEmpty()) ? HttpStatus.OK : HttpStatus.ACCEPTED;
            message = (!list.isEmpty()) ? "Listado de enfermedades" : "No hay enfermedades para listar";
        } catch (Exception e) {
            message = "Ocurrio un error, No se puede consultar la lista de enfermedades: " + e.getLocalizedMessage();
        }
        return jsonResponse.ResponseHttp(message, status, list);
    }

    @Override
    public ResponseEntity<String> tblIllness(Integer id) {
        ResponseEntityJson jsonResponse = new ResponseEntityJson();
        String message = "";
        HttpStatus status = HttpStatus.ACCEPTED;

        EnfermedadesReadModel enfermedadesReadModel = enfermedadesRepository.getById(id);
        EnfermedadesReadDto enferReadDtoResponse = null;
        if (enfermedadesReadModel != null) {
            enferReadDtoResponse = new EnfermedadesReadDto(enfermedadesReadModel.getId(),
                    enfermedadesReadModel.getNameTables(),
                    enfermedadesReadModel.getFechaCreacion(), enfermedadesReadModel.getEstado());
            status = HttpStatus.OK;
            message = "Información de la enfermedad";
        } else {
            message = "La enfermedad consultada no se encuentra registrada";
        }
        return jsonResponse.ResponseHttp(message, status, enferReadDtoResponse);
    }

    @Override
    public EnfermedadesReadDto findIllnessById(Integer id) {
        EnfermedadesReadModel enfermedadesReadModel = enfermedadesRepository.getById(id);
        EnfermedadesReadDto enferReadDtoResponse = null;
        if (enfermedadesReadModel != null) {
            enferReadDtoResponse = new EnfermedadesReadDto(enfermedadesReadModel.getId(),
                    enfermedadesReadModel.getNameTables(),
                    enfermedadesReadModel.getFechaCreacion(), enfermedadesReadModel.getEstado());
        }
        return enferReadDtoResponse;
    }

}
