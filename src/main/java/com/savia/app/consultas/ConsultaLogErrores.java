package com.savia.app.consultas;

import com.savia.app.constants.EnumNombreColumnasTablaCmEnfermedad;
import com.savia.app.util.ClassUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("unchecked")
@Service
public class ConsultaLogErrores extends ConsultasAbstract {

    private final Logger LOG = LoggerFactory.getLogger(ConsultaLogErrores.class);

    public List<Object> getPacienteError(int idEnfermedad, int limit, int page, String desde, String hasta) {
        final String nombreTablaEnfermedadPaso=nombreTablaEnfermedad(idEnfermedad, EnumNombreColumnasTablaCmEnfermedad.nombre_tabla_paso.toString());
        List<Object> listPacienteError = new ArrayList<Object>();
        try {
            String pureSql = "SELECT cep.* ";
            pureSql += " FROM " + nombreTablaEnfermedadPaso + " as cep ";
            pureSql += " WHERE cep.campo_leido = 1 AND";
            pureSql += " DATE(concat(SUBSTRING_INDEX(SUBSTRING_INDEX(cep.clave_archivo, '-', 2), '-', -1),'-',";
            pureSql += " SUBSTRING_INDEX(SUBSTRING_INDEX(cep.clave_archivo, '-', 3), '-', -1),'-',";
            pureSql += " SUBSTRING_INDEX(SUBSTRING_INDEX(cep.clave_archivo, '-', 4), '-', -1)))";
            pureSql += " BETWEEN :desde AND :hasta ";
            pureSql += " ORDER BY cep.id ";
            pureSql += " LIMIT :pagina , :limite ;";
            
            query = entityManager.createNativeQuery(pureSql);
            query.setParameter("desde", desde);
            query.setParameter("hasta", hasta);
            query.setParameter("pagina", ((page - 1) * limit));
            query.setParameter("limite", limit);

            listPacienteError = query.getResultList();

        } catch (Exception e) {
            LOG.error("Ocurrio un error en el metodo: '" + ClassUtil.getCurrentMethodName(this.getClass()) + "'");
        }
        return listPacienteError;
    }

    @Override
    public List<Object> getListAllColumTable(int idEnfermedad) {
        List<Object> listaColumnas = null;
        final String nombreTablaEnfermedadPaso=nombreTablaEnfermedad(idEnfermedad, EnumNombreColumnasTablaCmEnfermedad.nombre_tabla_paso.toString());
        try {
            String pureSql = "SELECT column_name ";
            pureSql += " FROM information_schema.columns";
            pureSql += " WHERE table_schema = :database ";
            pureSql += " AND table_name = :table ";
            pureSql += " ORDER BY ordinal_position";

            query = entityManager.createNativeQuery(pureSql);
            query.setParameter("database", dbName);
            query.setParameter("table", nombreTablaEnfermedadPaso);
            
            listaColumnas = query.getResultList();
        } catch (Exception e) {
            LOG.error("Ocurrio un error en el metodo: '" + ClassUtil.getCurrentMethodName(this.getClass()) + "'");
        }
        return listaColumnas;
    }
}


