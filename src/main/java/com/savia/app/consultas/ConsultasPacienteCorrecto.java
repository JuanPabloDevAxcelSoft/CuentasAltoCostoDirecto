package com.savia.app.consultas;

import com.savia.app.constants.EnumNombreColumnasTablaCmEnfermedad;
import com.savia.app.dto.ListarPacienteDto;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

@Service
public class ConsultasPacienteCorrecto extends  ConsultasAbstract{
    private final String nombreTablaPacienteFinal ="cm_paciente";
    private final String nombreTablaDetalleFinal="cm_detalle_paciente";
   @Override
    public List<Object> getListAllColumTable(int idEnfermedad) {
        final String nombreTablaEnfermedadFinal=nombreTablaEnfermedad(idEnfermedad, EnumNombreColumnasTablaCmEnfermedad.nom_tab_fin.toString());
        pureSql="SELECT DISTINCT (CONCAT(t.TABLE_NAME, '.', col.COLUMN_NAME)) ";
        pureSql+="FROM INFORMATION_SCHEMA.COLUMNS AS col ";
        pureSql+="INNER JOIN INFORMATION_SCHEMA.TABLES t ON col.TABLE_SCHEMA = t.TABLE_SCHEMA AND col.TABLE_NAME = t.TABLE_NAME ";
        pureSql+=" WHERE ";
        pureSql+=" col.TABLE_SCHEMA LIKE '"+dbName+"' ";
        pureSql+=" AND col.TABLE_NAME LIKE '"+ nombreTablaPacienteFinal +"' ";
        pureSql+=" OR col.TABLE_NAME LIKE '"+nombreTablaDetalleFinal+"' ";
        pureSql+=" OR col.TABLE_NAME LIKE '"+nombreTablaEnfermedadFinal+"' ";
        pureSql+="ORDER BY col.TABLE_NAME LIKE '"+nombreTablaPacienteFinal+"' DESC;";
        query=entityManager.createNativeQuery(pureSql);
        return query.getResultList();
    }

    public  List<Object> getPacienteCorrecto(ListarPacienteDto listarPacienteDto, boolean isFrond, String campos){
        final String nombreTablaEnfermedadFinal=nombreTablaEnfermedad(listarPacienteDto.getIdEnfermedad(), EnumNombreColumnasTablaCmEnfermedad.nom_tab_fin.toString());
        Integer page = listarPacienteDto.getPage();
        Integer limit = listarPacienteDto.getLimit();
        try{
            String pureSql="SELECT ";
            if(isFrond){
                pureSql += " pac.*, det.id AS detid, tblf.id AS finid ";
            }else {
                pureSql+=campos+" ";
            }
            pureSql += " FROM " + nombreTablaPacienteFinal + "  ";
            pureSql += " INNER JOIN " + nombreTablaDetalleFinal + "  ";
            pureSql += " ON "+nombreTablaPacienteFinal+".id = "+nombreTablaDetalleFinal+".id_paciente ";
            pureSql += " INNER JOIN " + nombreTablaEnfermedadFinal + "  ";
            pureSql += " ON "+nombreTablaPacienteFinal+".id = "+nombreTablaEnfermedadFinal+".id_paciente ";
            String where = this.getWhereSql(listarPacienteDto);
            pureSql += where;
            pureSql += " ORDER BY "+nombreTablaPacienteFinal+".id ";
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
            where +=  nombreTablaPacienteFinal+".tipo_identificacion = '" + lista.getTipoDocumento() + "' AND ";
            where += nombreTablaPacienteFinal+".numero_identificacion = '" + lista.getTipoDocumento() + "' ";
            entrada = true;
        }

        if (!lista.getTipoDocumento().equals("") && (!entrada)) {
            where += nombreTablaPacienteFinal+".tipo_documento = '" + lista.getTipoDocumento() + "' ";
            entrada = true;
        }

        if ((!lista.getDesde().equals("")) && (!lista.getHasta().equals(""))) {
            if (entrada) {
                where += " AND";
            }
            where += nombreTablaPacienteFinal+".fecha_ingreso BETWEEN '" + lista.getDesde() + "' AND '" + lista.getHasta() + "'";
            entrada = true;
        }

        return (entrada) ? where : "";
    }
}
