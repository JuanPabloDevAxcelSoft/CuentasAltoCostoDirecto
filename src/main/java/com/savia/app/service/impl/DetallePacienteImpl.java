package com.savia.app.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.NoSuchElementException;

import com.google.gson.Gson;
import com.savia.app.dto.ListarPacienteDto;
import com.savia.app.dto.Pacientes;
import com.savia.app.model.CmDetallePaciente;
import com.savia.app.model.CmPaciente;
import com.savia.app.repository.CmDetallePacienteRepository;
import com.savia.app.service.EnfermedadesReadService;
import com.savia.app.util.ObjetoJson;
import com.savia.app.util.ObtenerColumnasTabla;
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
    ObtenerColumnasTabla obtenerColumnasTabla;

    @Override
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
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
            response.setMessage((listTemporal.isEmpty()) ? "No hay registros para mostrar" : "Cantidad de resultados encontrados : " + listTemporal.size());
            response.setStatus((listTemporal.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
            response.setData(listTemporal);

        } catch (Exception e) {
            response.setMessage("Ocurrio un error al momento de consultar el detalle : " + e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    private String getWhereSql(ListarPacienteDto lista) {
        String where = " WHERE ";
        boolean entrada = false;

        if ((!lista.getTipoDocumento().equals("")) && (!lista.getDocumento().equals(""))) {
            where += " pac.tipo_identificacion = '" + lista.getTipoDocumento() + "' AND ";
            where += " pac.numero_identificacion = '" + lista.getTipoDocumento() + "' ";
            entrada = true;
        }

        if (!lista.getTipoDocumento().equals("") && (!entrada)) {
            where += " pac.tipo_documento = '" + lista.getTipoDocumento() + "' ";
            entrada = true;
        }

        if ((!lista.getDesde().equals("")) && (!lista.getHasta().equals(""))) {
            if (entrada) {
                where += " AND";
            }
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
    public CmDetallePaciente getDetallePacienteById(int idDetallePaciente) {
        try {
            Long id = Long.valueOf(idDetallePaciente);
            return cmDetallePacienteRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseEntity<ResponsePaciente> getLogErrores(ListarPacienteDto listarPacienteDto) {
        ResponsePaciente responsePaciente = new ResponsePaciente();
        String tablaPaso = enfermedadesReadService.getNombreTablaGeneric("nombre_tabla_paso", listarPacienteDto.getIdEnfermedad());
        Integer page = listarPacienteDto.getPage();
        Integer limit = listarPacienteDto.getLimit();
        try {
            List<Object> listNombreColumnas = obtenerColumnasTabla.getListAllColumTable(tablaPaso);
            String pureSql = "SELECT cep.* ";
            pureSql += " FROM " + tablaPaso + " as cep ";
            pureSql += " WHERE cep.campo_leido=1 AND ";
            pureSql += "DATE(concat(SUBSTRING_INDEX(SUBSTRING_INDEX(cep.clave_archivo, '-', 2), '-', -1),'-',";
            pureSql += "SUBSTRING_INDEX(SUBSTRING_INDEX(cep.clave_archivo, '-', 3), '-', -1),'-',";
            pureSql += "SUBSTRING_INDEX(SUBSTRING_INDEX(cep.clave_archivo, '-', 4), '-', -1)))";
            pureSql += " BETWEEN '" + listarPacienteDto.getDesde() + "' AND '" + listarPacienteDto.getHasta() + "' ";
            pureSql += " ORDER BY cep.id ";
            pureSql += " LIMIT " + ((page - 1) * limit) + ", " + limit + ";";
            Query query = entityManager.createNativeQuery(pureSql);
            List<Object> listPacienteEror = query.getResultList();
            String json="";
            for (int i = 0; i < listPacienteEror.size(); i++) {
                List<Object> listPacienteErrorUno=Arrays.asList(listPacienteEror.get(i));
                 json=json+setConvertListToJson(listNombreColumnas,listPacienteErrorUno);
            }
            responsePaciente.setMessage((listPacienteEror.isEmpty()) ? "No hay registros para mostrar" : "Cantidad de resultados encontrados : " + listPacienteEror.size());
            responsePaciente.setStatus((listPacienteEror.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
            responsePaciente.setItems(json);
        } catch (Exception e) {
            responsePaciente.setMessage("Ocurrio un error al momento de consultar los log de errores:" + e.getMessage());
        }

        return ResponseEntity.ok().body(responsePaciente);
    }

    private String setConvertListToJson(List<?> listaKey, List<?> listaValor) {
        List<ObjetoJson> listObjetoJson = new ArrayList<ObjetoJson>();
        System.out.println(listaKey.size()+"  "+ listaValor.size());
        if (!listaValor.isEmpty()) {
            for (int i = 0; i < listaKey.size(); i++) {
                listObjetoJson.add(new ObjetoJson(listaKey.get(i).toString(), listaValor.get(i).toString()));
            }
            return  new Gson().toJson(listObjetoJson);
        }
        else {
            return "";
        }
    }
}