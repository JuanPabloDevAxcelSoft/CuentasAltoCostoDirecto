package com.savia.app.consultas;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultasEstructuraTabla extends ConsultasAbstract{
    public List<Object> getListAllColumTable(String nombreTabla) {
        try {
            String pureSql = "SELECT column_name ";
            pureSql += " FROM information_schema.columns ";
            pureSql += "WHERE table_schema='" + dbName + "' ";
            pureSql += "and table_name='" + nombreTabla + "' ";
            pureSql += "ORDER BY ordinal_position";
            query = entityManager.createNativeQuery(pureSql);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
