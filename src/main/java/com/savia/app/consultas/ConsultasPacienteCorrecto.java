package com.savia.app.consultas;

import com.savia.app.constants.EnumNombreColumnasTablaCmEnfermedad;
import com.savia.app.dto.ListarPacienteDto;
import com.savia.app.util.ClassUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Service
public class ConsultasPacienteCorrecto extends ConsultasAbstract {

    private final Logger LOG = LoggerFactory.getLogger(ConsultasPacienteCorrecto.class);


    private final String nombreTablaPacienteFinal = "cm_paciente";
    private final String nombreTablaDetalleFinal = "cm_detalle_paciente";

    @Override
    public List<Object> getListAllColumTable(int idEnfermedad) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
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
        }finally {
            entityManager.close();
        }
        return listaFinal;
    }
    @CacheEvict(allEntries = true)
    public List<Object> getPacienteCorrecto(ListarPacienteDto listarPacienteDto, boolean bandera, String campos, boolean contador) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Object> listaFinal = null;
        final String nombreTablaEnfermedadFinal = nombreTablaEnfermedad(
                listarPacienteDto.getIdEnfermedad(),
                EnumNombreColumnasTablaCmEnfermedad.nom_tab_fin.toString());
        Integer page = listarPacienteDto.getPage();
        Integer limit = listarPacienteDto.getLimit();
        String claveArchivo=listarPacienteDto.getClaveArchivo();
        try {
            String pureSql = "SELECT ";
            pureSql+=(contador)?"COUNT(*) ":(bandera) ? " "+nombreTablaPacienteFinal+".*, "+nombreTablaDetalleFinal+".id AS detid,  "+nombreTablaEnfermedadFinal+".id AS finid  " : campos + " ";
            pureSql += " FROM " + nombreTablaPacienteFinal + "  ";
            pureSql += " INNER JOIN " + nombreTablaDetalleFinal + "  ";
            pureSql += " ON " + nombreTablaPacienteFinal + ".id = " + nombreTablaDetalleFinal + ".id_paciente ";
            pureSql += " INNER JOIN " + nombreTablaEnfermedadFinal + "  ";
            pureSql += " ON " + nombreTablaPacienteFinal + ".id = " + nombreTablaEnfermedadFinal + ".id_paciente ";
            String where = this.getWhereSql(listarPacienteDto);
            pureSql+="WHERE "+nombreTablaPacienteFinal+".clave_archivo = '"+claveArchivo+"' ";
            pureSql += where;
            pureSql += " ORDER BY " + nombreTablaPacienteFinal + ".id ";
            pureSql += (contador)? " ;":" LIMIT :pagina , :limite ;";
            Query query = entityManager.createNativeQuery(pureSql);
            if(!contador){
                query.setParameter("pagina", ((page - 1) * limit));
                query.setParameter("limite", limit);
            }

            listaFinal = query.getResultList();
        } catch (Exception e) {
            LOG.error("Ocurrio un error en el metodo: '" + ClassUtil.getCurrentMethodName(this.getClass()) + "' "+e.getMessage());
        }finally {
            entityManager.close();
        }
        return listaFinal;
    }

    private String getWhereSql(ListarPacienteDto lista) {
        String where = " AND ";
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
        if(!lista.getNovedades().equals("")){
            if (entrada) {
                where += " AND ";
            }
            where+= nombreTablaPacienteFinal+ ".novedades IN ("+lista.getNovedades()+") ";
            entrada = true;
        }

        if ((!lista.getDesde().equals("")) && (!lista.getHasta().equals(""))) {
            if (entrada) {
                where += " AND ";
            }
            where += nombreTablaPacienteFinal + ".fecha_ingreso BETWEEN '" + lista.getDesde() + "' AND '"
                    + lista.getHasta() + "'";
            entrada = true;
        }

        return (entrada) ? where : "";
    }
}
