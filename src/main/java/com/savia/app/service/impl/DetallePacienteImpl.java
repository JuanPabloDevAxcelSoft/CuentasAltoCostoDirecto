package com.savia.app.service.impl;

import java.util.List;

import com.savia.app.dto.ListarPacienteDto;
import com.savia.app.model.CmPaciente;
import com.savia.app.service.EnfermedadesReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.savia.app.repository.DetallePacienteRepository;
import com.savia.app.service.DetallePacienteService;
import com.savia.app.vo.ResponseMessage;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;

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
    public ResponseEntity<ResponseMessage> getDetallePaciente(ListarPacienteDto listarPacienteDto) {
        ResponseMessage response = new ResponseMessage();
        // sacando nombre de la tabla final
        String nombreTablaFinal = enfermedadesReadService.nomTabFin(listarPacienteDto.getIdEnfermedad());
        // page
        int limit = listarPacienteDto.getLimit();
        int page = (listarPacienteDto.getPage() - 1) * limit;
        try {
            String pureSql = "SELECT cm_paciente.*, cm_detalle_paciente.id as id_detalle_paciente, " + nombreTablaFinal
                    + ".id as id_hemofilia_paciente\n";
            pureSql += " FROM cm_paciente\n";
            pureSql += " JOIN cm_detalle_paciente ON cm_paciente.id = cm_detalle_paciente.id_paciente ";
            pureSql += "JOIN cm_paciente_hemofilia ON cm_paciente.id = " + nombreTablaFinal + ".id_paciente\n";
            pureSql += "WHERE ";
            boolean estadoMayorvali = false;
            if (!listarPacienteDto.getTipoDocumento().equals("")) {
                estadoMayorvali = true;
                pureSql += "tipo_identificacion ='" + listarPacienteDto.getTipoDocumento() + "' ";
            }
            if (!listarPacienteDto.getTipoDocumento().equals("") && (!listarPacienteDto.getDocumento().equals(""))) {
                pureSql += " AND numero_identificacion = '" + listarPacienteDto.getDocumento() + "' ";
            }
            if (!listarPacienteDto.getDesde().equals("") && (!listarPacienteDto.getHasta().equals(""))) {
                if (estadoMayorvali) {
                    pureSql += " AND ";
                }
                estadoMayorvali = true;
                pureSql += "  fecha_ingreso BETWEEN '" + listarPacienteDto.getDesde() + "' AND '"
                        + listarPacienteDto.getHasta() + "' ";
            }
            if (estadoMayorvali == false) {
                pureSql.replace("WHERE", "");
            }
            pureSql += "limit " + limit + " offset " + page + ";";

            Query query = entityManager.createNativeQuery(pureSql);
            List listPaciente = query.getResultList();
            response.setMessage((listPaciente.isEmpty()) ? "No hay registros para mostrar"
                    : "Cantidad de resultados encontrados : " + listPaciente.size());
            response.setStatus((listPaciente.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
            response.setData(Arrays.asList(listPaciente.toArray()));
            response.setData(listPaciente);
        } catch (Exception e) {
            response.setMessage("Ocurrio un error al momento de consultar el detalle : " + e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

}