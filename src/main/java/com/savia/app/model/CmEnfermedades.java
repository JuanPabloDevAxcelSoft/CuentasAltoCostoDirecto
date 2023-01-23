package com.savia.app.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cm_enfermedades")
public class CmEnfermedades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre_tabla")
    private String nombreTabla;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;

    private Boolean estado;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    public CmEnfermedades() {
    }

    public CmEnfermedades(String nombreTabla, String nombre) {
        this.nombreTabla = nombreTabla;
        this.nombre = nombre;
        this.fechaCreacion = new Date();
        this.estado = true;

    }

    public CmEnfermedades(Integer id, String nombreTabla, String nombre) {
        this.id = id;
        this.nombreTabla = nombreTabla;
        this.nombre = nombre;
        this.fechaCreacion = new Date();
        this.estado = true;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
