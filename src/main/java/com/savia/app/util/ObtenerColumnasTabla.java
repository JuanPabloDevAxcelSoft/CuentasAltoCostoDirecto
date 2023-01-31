package com.savia.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@SuppressWarnings("unchecked")
@Service("obtenerColumnasTabla")
public class ObtenerColumnasTabla {

    private final Logger LOG = LoggerFactory.getLogger(ObtenerColumnasTabla.class);

    @PersistenceContext
    EntityManager entityManager;

    @Value("${database.name}")
    String dbName;

    public List<Object> getListAllColumTable(String nombreTabla) {
        List<Object> listaColumnas = null;
        try {
            String pureSql = "SELECT column_name ";
            pureSql += " FROM information_schema.columns ";
            pureSql += " WHERE table_schema = :database ";
            pureSql += " AND table_name = :table ";
            pureSql += " ORDER BY ordinal_position";

            Query query = entityManager.createNativeQuery(pureSql);
            query.setParameter("database", dbName);
            query.setParameter("table", nombreTabla);
        
            listaColumnas = query.getResultList();
        } catch (Exception e) {
            LOG.error("Ocurrio un error en el metodo: '" + ClassUtil.getCurrentMethodName(this.getClass()) + "'");
        }
        return listaColumnas;
    }
}
