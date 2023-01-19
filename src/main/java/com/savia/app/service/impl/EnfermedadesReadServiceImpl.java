package com.savia.app.service.impl;

import com.savia.app.model.ReadCmEnfermedades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.savia.app.dto.EnfermedadesReadDto;
import com.savia.app.repository.EnfermedadesReadRepository;
import com.savia.app.service.EnfermedadesReadService;

import com.savia.app.vo.ResponseMessage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EnfermedadesReadServiceImpl implements EnfermedadesReadService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    EnfermedadesReadRepository enfermedadesRepository;

    @Override
    public ResponseEntity<ResponseMessage> allIllness() {
        ResponseMessage response = new ResponseMessage();
        List<ReadCmEnfermedades> list = new ArrayList<>();
        try {
            list = enfermedadesRepository.findAllByEstado(true);
            response.setStatus((!list.isEmpty()) ? HttpStatus.OK : HttpStatus.ACCEPTED);
            response.setMessage((!list.isEmpty()) ? "Listado de enfermedades" : "No hay enfermedades para listar");
            response.setData(Arrays.asList(list.toArray()));
        } catch (Exception e) {
            response.setMessage(
                    "Ocurrio un error, No se puede consultar la lista de enfermedades: " + e.getLocalizedMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<ResponseMessage> tblIllness(Integer id) {
        ResponseMessage response = new ResponseMessage();

        ReadCmEnfermedades enfermedadesReadModel = enfermedadesRepository.getById(id);
        EnfermedadesReadDto enferReadDtoResponse = null;
        if (enfermedadesReadModel != null) {
            enferReadDtoResponse = new EnfermedadesReadDto(enfermedadesReadModel.getId(),
                    enfermedadesReadModel.getNomTabFin(),
                    enfermedadesReadModel.getFechaCreacion(), enfermedadesReadModel.getEstado());

            response.setMessage("Información de la enfermedad");
            response.setStatus(HttpStatus.OK);
            response.setItem(enferReadDtoResponse);
        } else {
            response.setMessage("La enfermedad consultada no se encuentra registrada");
        }
        return ResponseEntity.ok().body(response);
    }

    @Override
    public EnfermedadesReadDto findIllnessById(Integer id) {
        ReadCmEnfermedades enfermedadesReadModel = enfermedadesRepository.getById(id);
        EnfermedadesReadDto enferReadDtoResponse = null;
        if (enfermedadesReadModel != null) {
            enferReadDtoResponse = new EnfermedadesReadDto(enfermedadesReadModel.getId(),
                    enfermedadesReadModel.getNomTabFin(),
                    enfermedadesReadModel.getFechaCreacion(), enfermedadesReadModel.getEstado());
        }
        return enferReadDtoResponse;
    }

    @Override
    public String nomtabFin(Integer id) {
        String sql= "select cm_enfermedades.nom_tab_fin from cm_enfermedades  where id ="+id+" and Estado=1;";
        Query query= entityManager.createNativeQuery(sql);

        return query.getSingleResult().toString();
    }

}
