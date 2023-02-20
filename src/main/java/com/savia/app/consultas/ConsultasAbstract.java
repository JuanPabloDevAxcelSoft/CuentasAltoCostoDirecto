package com.savia.app.consultas;

import com.savia.app.service.EnfermedadesReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public abstract class ConsultasAbstract {
    @Autowired
    protected EntityManagerFactory entityManagerFactory;

    @Autowired
    protected EnfermedadesReadService enfermedadesReadService;

    @Value("${database.name}")
    protected String dbName;

    protected String pureSql = "";

    protected Query query;

    public String nombreTablaEnfermedad(int idEnfermedad, String nombreColumna) {
        return enfermedadesReadService.getNombreTablaGeneric(nombreColumna, idEnfermedad);
    }

    public abstract List<Object> getListAllColumTable(int idEnfermedad);

    public ConsultasAbstract() {
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
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
