package com.savia.app.util;

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

    public List<Object> getPacienteError(String tablaPaso,int limit, int page, String desde,String hasta){
        List<Object> listPacienteError=new ArrayList<Object>();
        try{
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

        }catch (Exception e){
            e.printStackTrace();
        }
        return listPacienteError;
    }
}
