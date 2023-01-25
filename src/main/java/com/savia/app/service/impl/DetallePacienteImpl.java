package com.savia.app.service.impl;

import java.util.List;

import com.savia.app.dto.ListarPacienteDto;
import com.savia.app.dto.Pacientes;
import com.savia.app.model.CmPaciente;
import com.savia.app.service.EnfermedadesReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.savia.app.repository.CmPacienteRepository;
import com.savia.app.service.DetallePacienteService;
import com.savia.app.vo.ResponseMessage;
import com.savia.app.vo.ResponsePaciente;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class DetallePacienteImpl implements DetallePacienteService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CmPacienteRepository cmPacienteRepository;

    @Autowired
    EnfermedadesReadService enfermedadesReadService;

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
    public ResponseEntity<ResponsePaciente> getCmPaciente(ListarPacienteDto listarPacienteDto) {
        ResponsePaciente response = new ResponsePaciente();

        String tablaFinal = enfermedadesReadService.getNombreTablaGeneric("nom_tab_fin", listarPacienteDto.getIdEnfermedad());
        final String tblPaciente = "cm_paciente";
        final String tblDetalle = "cm_detalle_paciente";

        Integer page = listarPacienteDto.getPage();
        Integer limit = listarPacienteDto.getLimit();

        try {
            String pureSql = "SELECT pac.*, det.id AS detid, tblf.id AS finid ";
            pureSql += " FROM " + tblPaciente + " AS pac ";
            pureSql += " INNER JOIN " + tblDetalle + " AS det ";
            pureSql += " ON pac.id = det.id_paciente ";
            pureSql += " INNER JOIN " + tablaFinal + " AS tblf ";
            pureSql += " ON pac.id = tblf.id_paciente ";
            pureSql += " ORDER BY pac.id ";
            String where = this.getWhereSql(listarPacienteDto);
            pureSql += where;
            pureSql += " LIMIT " + ((page - 1) * limit) + ", " + limit + ";";

            Query query = entityManager.createNativeQuery(pureSql);
            
            List<Object> listTemporal = query.getResultList();
            List<Pacientes> listaFinal = this.setConvertListObject(listTemporal);
            
            response.setMessage((listTemporal.isEmpty()) ? "No hay registros para mostrar" : "Cantidad de resultados encontrados : " + listTemporal.size());
            response.setStatus((listTemporal.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
            response.setData(listaFinal);

        } catch (Exception e) {
            response.setMessage("Ocurrio un error al momento de consultar el detalle : " + e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    private String getWhereSql (ListarPacienteDto lista) {
        String where = " WHERE ";
        boolean entrada = false;

        if ((!lista.getTipoDocumento().equals("")) && (!lista.getDocumento().equals(""))) {
            where += " pac.tipo_identificacion = '"+lista.getTipoDocumento()+"' AND ";
            where += " pac.numero_identificacion = '"+lista.getTipoDocumento()+"' ";
            entrada = true;
        }

        if (!lista.getTipoDocumento().equals("") && (!entrada)) {
            where += " pac.tipo_documento = '"+lista.getTipoDocumento()+"' ";
            entrada = true;
        }

        if ((!lista.getDesde().equals("")) && (!lista.getHasta().equals(""))) {
            where += "pac.fecha_ingreso BETWEEN '" + lista.getDesde() + "' AND '" + lista.getHasta() + "'";
            entrada = true;
        }

        return (entrada) ? where : "";
    }

    private List<Pacientes> setConvertListObject(List<Object> list) {
        List<Pacientes> listaFinal = new ArrayList<>();
        for (Object item : list) {
            listaFinal.add(new Pacientes(item));
        }
        return listaFinal;
    }


    @Override
    public ResponseEntity<ResponseMessage> getDetallePaciente(int idDetallePaciente) {
        ResponseMessage response = new ResponseMessage();
        try {
            String pureSql = "SELECT * FROM cm_detalle_paciente WHERE id=" + idDetallePaciente;
            Query query = entityManager.createNativeQuery(pureSql);
            List<Object> cmDetallePacienteList = new ArrayList<>();
            cmDetallePacienteList.add(query.getSingleResult());
            response.setData(cmDetallePacienteList);
        } catch (NullPointerException e) {
            response.setMessage("el registro se encuentra vació ");
            e.printStackTrace();
        } catch (NoResultException e) {
            response.setMessage("No se encontró ningún registro con ese id");
        }
        return ResponseEntity.ok().body(response);
    }
}