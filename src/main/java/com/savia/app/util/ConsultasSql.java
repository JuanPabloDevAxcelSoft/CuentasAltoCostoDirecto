package com.savia.app.util;

import com.savia.app.dto.ListarPacienteDto;
import com.savia.app.repository.CmPacienteRepository;
import com.savia.app.service.EnfermedadesReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultasSql {
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    CmPacienteRepository cmPacienteRepository;
    @Autowired
    EnfermedadesReadService enfermedadesReadService;

    @Value("${database.name}")
    String dbName;

    public List<Object> getListAllColumTable(String nombreTabla) {
        try {
            String pureSql = "SELECT column_name ";
            pureSql += " FROM information_schema.columns ";
            pureSql += "WHERE table_schema='" + dbName + "' ";
            pureSql += "and table_name='" + nombreTabla + "' ";
            pureSql += "ORDER BY ordinal_position";
            Query query = entityManager.createNativeQuery(pureSql);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Object> getPacienteError(String tablaPaso, int limit, int page, String desde, String hasta) {
        List<Object> listPacienteError = new ArrayList<Object>();
        try {
            String pureSql = "SELECT cep.* ";
            pureSql += " FROM " + tablaPaso + " as cep ";
            pureSql += " WHERE cep.campo_leido=1 AND ";
            pureSql += "DATE(concat(SUBSTRING_INDEX(SUBSTRING_INDEX(cep.clave_archivo, '-', 2), '-', -1),'-',";
            pureSql += "SUBSTRING_INDEX(SUBSTRING_INDEX(cep.clave_archivo, '-', 3), '-', -1),'-',";
            pureSql += "SUBSTRING_INDEX(SUBSTRING_INDEX(cep.clave_archivo, '-', 4), '-', -1)))";
            pureSql += " BETWEEN '" + desde + "' AND '" + hasta + "' ";
            pureSql += " ORDER BY cep.id ";
            pureSql += " LIMIT " + ((page - 1) * limit) + ", " + limit + ";";
            Query query = entityManager.createNativeQuery(pureSql);
            listPacienteError = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPacienteError;
    }
    public  List<Object> getPacienteCorrecto(ListarPacienteDto listarPacienteDto,boolean isFrond){
        String tablaFinal = enfermedadesReadService.getNombreTablaGeneric("nom_tab_fin",
                listarPacienteDto.getIdEnfermedad());
        final String tblPaciente = "cm_paciente";
        final String tblDetalle = "cm_detalle_paciente";
        Integer page = listarPacienteDto.getPage();
        Integer limit = listarPacienteDto.getLimit();
        try{
            String pureSql="SELECT ";
            if(isFrond){
                pureSql += " pac.*, det.id AS detid, tblf.id AS finid ";
            }else {
                pureSql+="*";
            }
            pureSql += " FROM " + tblPaciente + " AS pac ";
            pureSql += " INNER JOIN " + tblDetalle + " AS det ";
            pureSql += " ON pac.id = det.id_paciente ";
            pureSql += " INNER JOIN " + tablaFinal + " AS tblf ";
            pureSql += " ON pac.id = tblf.id_paciente ";
            String where = this.getWhereSql(listarPacienteDto);
            pureSql += where;
            pureSql += " ORDER BY pac.id ";
            pureSql += " LIMIT " + ((page - 1) * limit) + ", " + limit + ";";
            Query query = entityManager.createNativeQuery(pureSql);
            return query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

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

    public List<Object[]> consultaPrueba(){
        String pureSql="CONCAT('SELECT ', " +
                "(SELECT REPLACE(GROUP_CONCAT(COLUMN_NAME), " +
                "'id,', '') FROM INFORMATION_SCHEMA.COLUMNS WHERE " +
                "TABLE_NAME = 'cm_paciente' AND TABLE_SCHEMA = " +
                "'db_savia'), ' FROM cm_paciente');" ;
        Query query= entityManager.createNativeQuery(pureSql);
        return query.getResultList();
    }
}
