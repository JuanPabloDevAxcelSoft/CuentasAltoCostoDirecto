package com.savia.hemofilia.dto;

import java.util.Date;

public class IpsReadDto {
    private Integer id;
    private String nameIps;
    private Date fechaCreacion;
    private Boolean estado;

    public IpsReadDto(Integer id, String nameIps, Date fechaCreacion, Boolean estado) {
        this.id = id;
        this.nameIps = nameIps;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameIps() {
        return nameIps;
    }

    public void setNameIps(String nameIps) {
        this.nameIps = nameIps;
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
