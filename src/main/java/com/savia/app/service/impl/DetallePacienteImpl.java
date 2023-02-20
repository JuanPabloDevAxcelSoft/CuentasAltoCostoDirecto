package com.savia.app.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.savia.app.consultas.ConsultaLogErrores;
import com.savia.app.consultas.ConsultasPacienteCorrecto;
import com.savia.app.dto.ListarPacienteDto;
import com.savia.app.dto.Pacientes;
import com.savia.app.repository.CmDetallePacienteRepository;
import com.savia.app.util.ConvertListArrayToJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.savia.app.service.DetallePacienteService;
import com.savia.app.vo.ResponseJson;
import com.savia.app.vo.ResponseMessage;
import com.savia.app.vo.ResponsePaciente;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Service
public class DetallePacienteImpl implements DetallePacienteService {
    @PersistenceContext
    private EntityManager entityManager;

    
    @Autowired
    private CmDetallePacienteRepository cmDetallePacienteRepository;

    @Autowired
    private ConsultasPacienteCorrecto consultasPacienteCorrecto;
    
    @Autowired
    private ConsultaLogErrores consultaLogErrores;

    @Autowired
    private ConvertListArrayToJson convertListArrayToJson;


    @Override
    public ResponseEntity<ResponsePaciente> getCmPaciente(ListarPacienteDto listPaciente) {
        ResponsePaciente response = new ResponsePaciente();
        try {
            response.setItems(consultasPacienteCorrecto.getPacienteCorrecto(listPaciente,true,"",true).toString());
            List<Object> listTemporal = consultasPacienteCorrecto.getPacienteCorrecto(listPaciente,true,"",false);
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
        Integer page = listarPacienteDto.getPage();
        Integer limit = listarPacienteDto.getLimit();
        try {
            List<Object> listNombreColumnas = consultaLogErrores.getListAllColumTable(listarPacienteDto.getIdEnfermedad());
            //Sancando los pacientes
            responseJsonGeneric.setItems(consultaLogErrores.getPacienteError(listarPacienteDto.getIdEnfermedad(),limit,page,listarPacienteDto.getDesde(),listarPacienteDto.getHasta(),true).toString());
            List<Object> listPacienteError = consultaLogErrores.getPacienteError(listarPacienteDto.getIdEnfermedad(),limit,page,listarPacienteDto.getDesde(),listarPacienteDto.getHasta(),false);
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