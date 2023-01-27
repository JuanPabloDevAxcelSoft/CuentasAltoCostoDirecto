package com.savia.app.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.savia.app.dto.ListarPacienteDto;
import com.savia.app.dto.Pacientes;
import com.savia.app.model.CmPaciente;
import com.savia.app.repository.CmDetallePacienteRepository;
import com.savia.app.service.EnfermedadesReadService;
import com.savia.app.util.ConvertListArrayToJson;
import com.savia.app.util.ConsultasSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.savia.app.repository.CmPacienteRepository;
import com.savia.app.service.DetallePacienteService;
import com.savia.app.vo.ResponseJson;
import com.savia.app.vo.ResponseMessage;
import com.savia.app.vo.ResponsePaciente;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unchecked")
@Service
public class DetallePacienteImpl implements DetallePacienteService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CmPacienteRepository cmPacienteRepository;
    @Autowired
    private CmDetallePacienteRepository cmDetallePacienteRepository;

    @Autowired
    EnfermedadesReadService enfermedadesReadService;

    @Autowired
    ConsultasSql consultasSql;

    @Autowired
    private ConvertListArrayToJson convertListArrayToJson;

    @Override
    @Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
    public ResponseEntity<ResponseMessage> getAllPacientePaginated() {
        ResponseMessage response = new ResponseMessage();
        List<CmPaciente> list = new ArrayList<>();
        try {
            list = cmPacienteRepository.findAll();
            response.setMessage((list.isEmpty()) ? "No hay registros para mostrar"
                    : "Cantidad de resultados encontrados : " + list.size());
            response.setStatus((list.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
            response.setData(Arrays.asList(list.toArray()));
        } catch (Exception e) {
            response.setMessage("Ocurrio un error : " + e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<ResponsePaciente> getCmPaciente(ListarPacienteDto listPaciente) {
        ResponsePaciente response = new ResponsePaciente();
        try {
            List<Object> listTemporal = consultasSql.getPacienteCorrecto(listPaciente,true,"");
            List<Pacientes> list = this.convertListArrayToJson.setConvertListObjectPaciente(listTemporal);
            response.setMessage((listTemporal.isEmpty()) ? "No hay registros para mostrar" : "Cantidad de resultados encontrados : " + listTemporal.size());
            response.setStatus((listTemporal.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
            response.setData(list);

        } catch (Exception e) {
            response.setMessage("Ocurrio un error al momento de consultar el detalle : " + e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }



    @Override
    public ResponseEntity<ResponseMessage> getDetallePacienteById(int idDetallePaciente) {
        ResponseMessage response = new ResponseMessage();
        try {
            Long id = Long.valueOf(idDetallePaciente);
            Optional<?> objectoDetalle = cmDetallePacienteRepository.findById(id);
            if (objectoDetalle != null) {
                response.setItem(objectoDetalle.get());
            }
            response.setMessage("Detalle del paciente");
            response.setStatus(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            response.setMessage("Ocurrio un error al momento de realizar la consulta : " + e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<ResponseJson> getLogErrores(ListarPacienteDto listarPacienteDto) {
        ResponseJson responseJsonGeneric = new ResponseJson();
        final String tablaPaso = enfermedadesReadService.getNombreTablaGeneric("nombre_tabla_paso",
                listarPacienteDto.getIdEnfermedad());
        Integer page = listarPacienteDto.getPage();
        Integer limit = listarPacienteDto.getLimit();
        try {
            List<Object> listNombreColumnas = consultasSql.getListAllColumTable(tablaPaso);
            //Sancando los pacientes
            List<Object> listPacienteError = consultasSql.getPacienteError(tablaPaso,limit,page,listarPacienteDto.getDesde(),listarPacienteDto.getHasta());
            responseJsonGeneric.setMessage((listPacienteError.isEmpty())
                    ? "No hay registros para mostrar"
                    : "Cantidad de resultados encontrados : " + listPacienteError.size());

            responseJsonGeneric.setStatus((listPacienteError.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
            ArrayList<Object> listAllResult = convertListArrayToJson.setConvertListArrayToListJson(listPacienteError,
                    listNombreColumnas);

            responseJsonGeneric.setData(listAllResult);
        } catch (Exception e) {
            responseJsonGeneric
                    .setMessage("Ocurrio un error al momento de consultar los log de errores:" + e.getMessage());
        }
        return ResponseEntity.ok().body(responseJsonGeneric);
    }
}