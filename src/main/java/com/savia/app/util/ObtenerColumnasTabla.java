package com.savia.app.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class ObtenerColumnasTabla {
    @PersistenceContext
    EntityManager entityManager;

    @Value("${database.name}")
    String dbName;
    public List<Object> getListAllColumTable(String nombreTabla){
        try{
            String pureSql="SELECT column_name ";
            pureSql+=" FROM information_schema.columns ";
            pureSql+="WHERE table_schema='"+dbName+"' ";
            pureSql+="and table_name='"+nombreTabla+"' ";
            pureSql+="ORDER BY ordinal_position";
            Query query= entityManager.createNativeQuery(pureSql);
            return query.getResultList();
       }catch (Exception e ){
            e.printStackTrace();
         return null;
        }
    }
}
