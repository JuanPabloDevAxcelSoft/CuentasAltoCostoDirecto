package com.savia.app.consultas;

import com.savia.app.constants.EnumNombreColumnasTablaCmEnfermedad;
import com.savia.app.util.ClassUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
@Service
public class ConsultaLogErrores extends ConsultasAbstract {

    private final Logger LOG = LoggerFactory.getLogger(ConsultaLogErrores.class);

    public List<Object> getPacienteError(int idEnfermedad, int limit, int page, String desde, String hasta, boolean contador) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        final String nombreTablaEnfermedadPaso=nombreTablaEnfermedad(idEnfermedad, EnumNombreColumnasTablaCmEnfermedad.nombre_tabla_paso.toString());
        List<Object> listPacienteError = new ArrayList<Object>();
        try {
            pureSql = "SELECT  ";
            pureSql+=(contador)?" COUNT(*)":" cep.*";
            pureSql += " FROM " + nombreTablaEnfermedadPaso + " as cep ";
            pureSql += " WHERE cep.campo_leido = 1 AND";
            pureSql += " DATE(concat(SUBSTRING_INDEX(SUBSTRING_INDEX(cep.clave_archivo, '-', 2), '-', -1),'-',";
            pureSql += " SUBSTRING_INDEX(SUBSTRING_INDEX(cep.clave_archivo, '-', 3), '-', -1),'-',";
            pureSql += " SUBSTRING_INDEX(SUBSTRING_INDEX(cep.clave_archivo, '-', 4), '-', -1)))";
            pureSql += " BETWEEN :desde AND :hasta ";
            pureSql += " ORDER BY cep.id ";
            pureSql += (contador)? " ;":" LIMIT :pagina , :limite ;";
            
            query = entityManager.createNativeQuery(pureSql);
            query.setParameter("desde", desde);
            query.setParameter("hasta", hasta);
            if(!contador){
                query.setParameter("pagina", ((page - 1) * limit));
                query.setParameter("limite", limit);
            }

            listPacienteError = query.getResultList();

        } catch (Exception e) {
            LOG.error("Ocurrio un error en el metodo: '" + ClassUtil.getCurrentMethodName(this.getClass()) + "'");
        }finally {
            entityManager.close();
        }
        return listPacienteError;
    }

    @Override
    public List<Object> getListAllColumTable(int idEnfermedad) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
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
        }finally {
            entityManager.close();
        }
        return listaColumnas;
    }
}



