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
import javax.persistence.Query;
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

        String tablaFinal = enfermedadesReadService.getNombreTablaGeneric("nom_tab_fin", listPaciente.getIdEnfermedad());
        final String tblPaciente = "cm_paciente";
        final String tblDetalle = "cm_detalle_paciente";

        final boolean banderaTipoNumeroDocumentos = (!listPaciente.getTipoDocumento().equals("")) && (!listPaciente.getDocumento().equals(""));
        final boolean banderaTipoDocumento= !listPaciente.getTipoDocumento().equals("");
        final boolean banderaFechasRango = (!listPaciente.getDesde().equals("")) && (!listPaciente.getHasta().equals(""));
        
        Integer page = listPaciente.getPage();
        Integer limit = listPaciente.getLimit();

        try {
            String pureSql = "SELECT pac.*, det.id AS detid, tblf.id AS finid ";
            pureSql += " FROM " + tblPaciente + " AS pac ";
            pureSql += " INNER JOIN " + tblDetalle + " AS det ";
            pureSql += " ON pac.id = det.id_paciente ";
            pureSql += " INNER JOIN " + tablaFinal + " AS tblf ";
            pureSql += " ON pac.id = tblf.id_paciente ";
            String where = this.getWhereSql(listPaciente);
            pureSql += where;
            pureSql += " ORDER BY pac.id ";
            pureSql += " LIMIT :pagina , :limite ;";

            Query query = entityManager.createNativeQuery(pureSql);
            query.setParameter("pagina", ((page - 1) * limit));
            query.setParameter("limite", limit);

            if (banderaTipoNumeroDocumentos) {
                query.setParameter("tipo", listPaciente.getTipoDocumento());
                query.setParameter("documento", listPaciente.getDocumento());
            }
            if (banderaTipoDocumento) {
                query.setParameter("tipo", listPaciente.getTipoDocumento());
            }
            if (banderaFechasRango) {
                query.setParameter("desde", listPaciente.getDesde());
                query.setParameter("hasta", listPaciente.getHasta());
            }

            List<Object> listTemporal = query.getResultList();
            List<Pacientes> list = this.convertListArrayToJson.setConvertListObjectPaciente(listTemporal);
            response.setMessage((listTemporal.isEmpty()) ? "No hay registros para mostrar" : "Cantidad de resultados encontrados : " + listTemporal.size());
            response.setStatus((listTemporal.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
            response.setData(list);

        } catch (Exception e) {
            response.setMessage("Ocurrio un error al momento de consultar el detalle : " + e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    private String getWhereSql(ListarPacienteDto lista) {
        String where = " WHERE ";
        boolean entrada = false;

        if ((!lista.getTipoDocumento().equals("")) && (!lista.getDocumento().equals(""))) {
            where += " pac.tipo_identificacion = :tipo AND ";
            where += " pac.numero_identificacion = :documento ";
            entrada = true;
        }

        if (!lista.getTipoDocumento().equals("") && (!entrada)) {
            where += " pac.tipo_identificacion = :tipo ";
            entrada = true;
        }

        if ((!lista.getDesde().equals("")) && (!lista.getHasta().equals(""))) {
            if (entrada) {
                where += " AND ";
            }
            where += "pac.fecha_ingreso BETWEEN :desde AND :hasta ";
            entrada = true;
        }
        return (entrada) ? where : "";
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