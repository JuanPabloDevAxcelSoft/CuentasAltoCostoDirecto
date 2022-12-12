package com.savia.app.dto;

import java.util.Date;

public class EnfermedadesReadDto {
    private Integer id;
    private String tabla;
    private Date fechaCreacion;
    private Boolean estado;

    public EnfermedadesReadDto(Integer id, String nameTables, Date fechaCreacion, Boolean estado) {
        this.id = id;
        this.tabla = nameTables;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameTables() {
        return tabla;
    }

    public void setNameTables(String nameTables) {
        this.tabla = nameTables;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
