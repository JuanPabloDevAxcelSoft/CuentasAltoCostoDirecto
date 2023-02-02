package com.savia.app.service.impl;

import com.savia.app.components.TaskBackComponent;
import com.savia.app.model.ReadCmEnfermedades;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.savia.app.dto.EnfermedadesReadDto;
import com.savia.app.repository.EnfermedadesReadRepository;
import com.savia.app.service.EnfermedadesReadService;

import com.savia.app.vo.ResponseMessage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EnfermedadesReadServiceImpl implements EnfermedadesReadService {
    private final Logger LOGGER = LoggerFactory.getLogger(EnfermedadesReadService.class);


    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    EnfermedadesReadRepository enfermedadesRepository;

    @Override
    public ResponseEntity<ResponseMessage> getAllEnfermedades() {
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
                    enfermedadesReadModel.getFechaCreacion(),
                    enfermedadesReadModel.getEstado());

            response.setMessage("Información de la enfermedad");
            response.setStatus(HttpStatus.OK);
            response.setItem(enferReadDtoResponse);
        } else {
            response.setMessage("La enfermedad consultada no se encuentra registrada");
        }
        return ResponseEntity.ok().body(response);
    }

    @Override
    public EnfermedadesReadDto findEnfermedadById(Integer id) {
        ReadCmEnfermedades enfermedadesReadModel = enfermedadesRepository.getById(id);
        EnfermedadesReadDto enferReadDtoResponse = null;
        if (enfermedadesReadModel != null) {
            enferReadDtoResponse = new EnfermedadesReadDto(enfermedadesReadModel.getId(),
                    enfermedadesReadModel.getNombreTabla(),
                    enfermedadesReadModel.getFechaCreacion(), enfermedadesReadModel.getEstado());
        }
        return enferReadDtoResponse;
    }

    /*
     * Metodo que retorna el nombre de la tabla segun el tipo de parametros
     */
    @Override
    public String getNombreTablaGeneric(String columna, Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String pureSql = "SELECT " + columna + " FROM cm_enfermedades AS enf ";
        pureSql += "WHERE enf.id = :id AND enf.estado = :estado ;";

        Query query = entityManager.createNativeQuery(pureSql);
        query.setParameter("id", id);
        query.setParameter("estado", 1);

        return (query.getSingleResult() == null) ? "" : query.getSingleResult().toString();
    }

    @Override
    public List<Integer> getAllId() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String pureSql="SELECT enf.id FROM cm_enfermedades AS enf ";
        pureSql+=" WHERE enf.estado= :estado ;";
        Query query= entityManager.createNativeQuery(pureSql);
        query.setParameter("estado",1);
        return query.getResultList();
    }
    @Override
    public List<Object> getCantidadValidar(String nombreTabla) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Object> listResultante = null;
        String pureQuery = "SELECT tab.id";
        pureQuery += " FROM " + nombreTabla + " AS tab";
        pureQuery += " WHERE tab.campo_leido = :estado;";
        try {
            System.out.println(pureQuery);
            Query query = entityManager.createNativeQuery(pureQuery);
            query.setParameter("estado", 0);
            listResultante = query.getResultList();
        } catch (Exception e) {
            this.LOGGER.info("Ocurrio un error : " + e.getMessage());
        }
        return listResultante;
    }
}
