package com.savia.app.consultas;

import com.savia.app.constants.EnumNombreColumnasTablaCmEnfermedad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ConsultasHistoricoArchivo  extends  ConsultasAbstract{
    private final Logger LOG = LoggerFactory.getLogger(ConsultasHistoricoArchivo.class);
    private final String nombreTablaPacienteFinal = "cm_paciente";
    @Override
    public List<Object> getListAllColumTable(int idEnfermedad) {
        return null;
    }
    public  List<Object> getHistoricoArchivo(int idEnfermedad, boolean contador,int limit, int page){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Object> listaFinal = null;
        try {
            final String nombreTablaEnfermedadPaso=nombreTablaEnfermedad(idEnfermedad, EnumNombreColumnasTablaCmEnfermedad.nombre_tabla_paso.toString());
            pureSql=(contador)? "SELECT COUNT(*) FROM (":"";
            pureSql+= " SELECT clave_archivo,nombre_archivo_original,fecha_ingreso ";
            pureSql+=" FROM "+nombreTablaPacienteFinal +"";
            pureSql+=" WHERE clave_archivo LIKE '"+idEnfermedad+"%' ";
            pureSql+=" UNION ";
            pureSql+="SELECT clave_archivo,nombre_archivo_original,";
            pureSql += " DATE(concat(SUBSTRING_INDEX(SUBSTRING_INDEX(clave_archivo, '-', 2), '-', -1),'-',";
            pureSql += " SUBSTRING_INDEX(SUBSTRING_INDEX(clave_archivo, '-', 3), '-', -1),'-',";
            pureSql += " SUBSTRING_INDEX(SUBSTRING_INDEX(clave_archivo, '-', 4), '-', -1))) ";
            pureSql+=" FROM "+nombreTablaEnfermedadPaso+" ";
            pureSql+=(contador)? " ) as conjunto_resultados ;":"LIMIT :pagina , :limite ;";
            query = entityManager.createNativeQuery(pureSql);
            if(!contador){
                query.setParameter("pagina", ((page - 1) * limit));
                query.setParameter("limite", limit);
            }

            listaFinal= query.getResultList();
        }catch (Exception e){
            LOG.error(e.getMessage());
        }finally {
            entityManager.close();
        }
        return listaFinal;
    }
}
