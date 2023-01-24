package com.savia.app.service.impl;

import java.util.List;

import com.savia.app.dto.ListarPacienteDto;
import com.savia.app.model.CmPaciente;
import com.savia.app.model.PacientesConsulta;
import com.savia.app.service.EnfermedadesReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.savia.app.repository.DetallePacienteRepository;
import com.savia.app.service.DetallePacienteService;
import com.savia.app.vo.ResponseMessage;
import com.savia.app.vo.ResponsePaciente;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
@SuppressWarnings("unchecked") 
@Service
public class DetallePacienteImpl implements DetallePacienteService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DetallePacienteRepository detallePacienteRepository;
    @Autowired
    EnfermedadesReadService enfermedadesReadService;

    @Override
    @Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
    public ResponseEntity<ResponseMessage> getAllPacientePaginated() {
        ResponseMessage response = new ResponseMessage();
        List<CmPaciente> list = new ArrayList<>();
        try {
            list = detallePacienteRepository.findAll();
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
    public ResponseEntity<ResponsePaciente> getDetallePaciente(ListarPacienteDto listarPacienteDto) {
        ResponsePaciente response = new ResponsePaciente();

        String tablaFinal = enfermedadesReadService.getNombreTablaFinal(listarPacienteDto.getIdEnfermedad());
        String tablaPaciente = "cm_paciente";
        String tablaDetalle = "cm_detalle_paciente";
        Integer page = listarPacienteDto.getPage();
        Integer limit = listarPacienteDto.getLimit();

        try {
            String pureSql = "SELECT pac.*, det.id AS id_detalle, tfi.id AS id_carga";
            pureSql += " FROM " + tablaPaciente + " AS pac";
            pureSql += " INNER JOIN " + tablaDetalle + " AS det ON ";
            pureSql += " pac.id = det.id_paciente ";
            pureSql += " INNER JOIN " + tablaFinal + " AS tfi ON ";
            pureSql += " pac.id = tfi.id_paciente ";
            pureSql += " ORDER BY pac.id ";
            String where = this.getWhere(listarPacienteDto);
            pureSql += where;
            pureSql += " LIMIT " + ((page - 1) * limit) + ", " + limit + ";";

            Query query = entityManager.createNativeQuery(pureSql);
            List<Object> listPacienteTemp = query.getResultList();

            List<PacientesConsulta> list = this.setConverListToListObject(listPacienteTemp);
            response.setMessage((!list.isEmpty()) ? "Lista de pacientes cargados" : "No hay registros para mostrar");
            response.setStatus(HttpStatus.OK);
            response.setData(list);

        } catch (Exception e) {
            response.setMessage("Ocurrio un error al momento de consultar el detalle : " + e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    private String getWhere(ListarPacienteDto lista) {
        String where = " WHERE ";
        boolean entrada = false;

        if ((!lista.getTipoDocumento().equals("")) && (!lista.getDocumento().equals(""))) {
            where += " tipo_identificacion = '" + lista.getTipoDocumento() + "' AND ";
            where += " numero_identificacion = '" + lista.getDocumento() + "'";
            entrada = true;
        }

        if (!lista.getTipoDocumento().equals("") && (!entrada)) {
            where += " tipo_identificacion = '" + lista.getTipoDocumento() + "'";
            entrada = true;
        }

        if ((!lista.getDesde().equals("")) && (!lista.getHasta().equals(""))) {
            where += " fecha_ingreso BETWEEN '" + lista.getDesde() + "' AND ";
            where += " '" + lista.getHasta() + "'";
            entrada = true;
        }
        return (entrada) ? where : "";
    }

    private List<PacientesConsulta> setConverListToListObject(List<Object> listPacienteTemp) {
        List<PacientesConsulta> listPaciente = new ArrayList<>();
        try {
            for (Object object : listPacienteTemp) {
                listPaciente.add(new PacientesConsulta(object));
            }
        } catch (Exception e) {
        }
        return listPaciente;
    }

}