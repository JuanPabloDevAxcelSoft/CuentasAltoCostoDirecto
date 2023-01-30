package com.savia.app.consultas;

import com.savia.app.constants.EnumNombreColumnasTablaCmEnfermedad;
import com.savia.app.service.EnfermedadesReadService;
import org.hibernate.query.internal.NativeQueryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public abstract class ConsultasAbstract {
    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    protected EnfermedadesReadService enfermedadesReadService;

    @Value("${database.name}")
    protected String dbName;

    protected String pureSql="";

    protected Query query;

    protected EnumNombreColumnasTablaCmEnfermedad enumNombreColumnasTablaCmEnfermedad;

    public String nombreTablaEnfermedad(int idEnfermedad, String nombreColumna){
        return enfermedadesReadService.getNombreTablaGeneric(nombreColumna,idEnfermedad);
    }
    public ConsultasAbstract() {
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EnfermedadesReadService getEnfermedadesReadService() {
        return enfermedadesReadService;
    }

    public void setEnfermedadesReadService(EnfermedadesReadService enfermedadesReadService) {
        this.enfermedadesReadService = enfermedadesReadService;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getPureSql() {
        return pureSql;
    }

    public void setPureSql(String pureSql) {
        this.pureSql = pureSql;
    }
}
