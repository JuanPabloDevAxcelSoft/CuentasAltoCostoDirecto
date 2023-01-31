package com.savia.app.consultas;

import com.savia.app.constants.EnumNombreColumnasTablaCmEnfermedad;
import com.savia.app.dto.ListarPacienteDto;
import com.savia.app.util.ClassUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

@SuppressWarnings("unchecked")
@Service
public class ConsultasPacienteCorrecto extends ConsultasAbstract {

    private final Logger LOG = LoggerFactory.getLogger(ConsultasPacienteCorrecto.class);

    private final String nombreTablaPacienteFinal = "cm_paciente";
    private final String nombreTablaDetalleFinal = "cm_detalle_paciente";

    @Override
    public List<Object> getListAllColumTable(int idEnfermedad) {
        List<Object> listaFinal = null;
        try {
            final String nombreTablaEnfermedadFinal = nombreTablaEnfermedad(idEnfermedad,
                    EnumNombreColumnasTablaCmEnfermedad.nom_tab_fin.toString());
            pureSql = "SELECT DISTINCT (CONCAT(t.TABLE_NAME, '.', col.COLUMN_NAME))";
            pureSql += " FROM INFORMATION_SCHEMA.COLUMNS AS col ";
            pureSql += " INNER JOIN INFORMATION_SCHEMA.TABLES t ON col.TABLE_SCHEMA = t.TABLE_SCHEMA";
            pureSql += " AND col.TABLE_NAME = t.TABLE_NAME WHERE ";
            pureSql += " col.TABLE_SCHEMA LIKE :database ";
            pureSql += " AND col.TABLE_NAME LIKE :tablapaciente ";
            pureSql += " OR col.TABLE_NAME LIKE :detallepaciente ";
            pureSql += " OR col.TABLE_NAME LIKE :tablafinal ";
            pureSql += " ORDER BY col.TABLE_NAME LIKE :tablapaciente DESC;";

            query = entityManager.createNativeQuery(pureSql);
            query.setParameter("database", dbName);
            query.setParameter("tablapaciente", nombreTablaPacienteFinal);
            query.setParameter("detallepaciente", nombreTablaDetalleFinal);
            query.setParameter("tablafinal", nombreTablaEnfermedadFinal);

            listaFinal = query.getResultList();
        } catch (Exception e) {
            LOG.error("Ocurrio un error en el metodo: '" + ClassUtil.getCurrentMethodName(this.getClass()) + "'");
        }
        return listaFinal;
    }

    public List<Object> getPacienteCorrecto(ListarPacienteDto listarPacienteDto, boolean bandera, String campos) {
        List<Object> listaFinal = null;
        final String nombreTablaEnfermedadFinal = nombreTablaEnfermedad(
                listarPacienteDto.getIdEnfermedad(),
                EnumNombreColumnasTablaCmEnfermedad.nom_tab_fin.toString());
        Integer page = listarPacienteDto.getPage();
        Integer limit = listarPacienteDto.getLimit();
        try {
            String pureSql = "SELECT ";
            pureSql += (bandera) ? " pac.*, det.id AS detid, tblf.id AS finid " : campos + " ";

            pureSql += " FROM " + nombreTablaPacienteFinal + "  ";
            pureSql += " INNER JOIN " + nombreTablaDetalleFinal + "  ";
            pureSql += " ON " + nombreTablaPacienteFinal + ".id = " + nombreTablaDetalleFinal + ".id_paciente ";
            pureSql += " INNER JOIN " + nombreTablaEnfermedadFinal + "  ";
            pureSql += " ON " + nombreTablaPacienteFinal + ".id = " + nombreTablaEnfermedadFinal + ".id_paciente ";
            String where = this.getWhereSql(listarPacienteDto);
            pureSql += where;
            pureSql += " ORDER BY " + nombreTablaPacienteFinal + ".id ";
            pureSql += " LIMIT :pagina , :limite ;";
            
            Query query = entityManager.createNativeQuery(pureSql);
            query.setParameter("pagina", ((page - 1) * limit));
            query.setParameter("limite", limit);

            listaFinal = query.getResultList();
        } catch (Exception e) {
            LOG.error("Ocurrio un error en el metodo: '" + ClassUtil.getCurrentMethodName(this.getClass()) + "'");
        }
        return listaFinal;
    }

    private String getWhereSql(ListarPacienteDto lista) {
        String where = " WHERE ";
        boolean entrada = false;

        if ((!lista.getTipoDocumento().equals("")) && (!lista.getDocumento().equals(""))) {
            where += nombreTablaPacienteFinal + ".tipo_identificacion = '" + lista.getTipoDocumento() + "' AND ";
            where += nombreTablaPacienteFinal + ".numero_identificacion = '" + lista.getTipoDocumento() + "' ";
            entrada = true;
        }

        if (!lista.getTipoDocumento().equals("") && (!entrada)) {
            where += nombreTablaPacienteFinal + ".tipo_documento = '" + lista.getTipoDocumento() + "' ";
            entrada = true;
        }

        if ((!lista.getDesde().equals("")) && (!lista.getHasta().equals(""))) {
            if (entrada) {
                where += " AND";
            }
            where += nombreTablaPacienteFinal + ".fecha_ingreso BETWEEN '" + lista.getDesde() + "' AND '"
                    + lista.getHasta() + "'";
            entrada = true;
        }

        return (entrada) ? where : "";
    }
}