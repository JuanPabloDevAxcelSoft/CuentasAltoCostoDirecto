/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.savia.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Angel Gonzalez
 */
@Entity
@Table(name = "cm_enfermedades")
public class ModelCmEnfermedades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre_tabla_paso")
    private String nombreTablaPaso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String nombre;
    private Boolean estado;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre_clase_validacion")
    private String nombreClaseValidacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nom_tab_fin")
    private String nomTabFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String novedades;


    public ModelCmEnfermedades() {
    }

    public ModelCmEnfermedades(Integer id) {
        this.id = id;
    }

    public ModelCmEnfermedades(Integer id, String nombreTablaPaso, String nombre, String nameEnfermedadFrond, String nomTabFin) {
        this.id = id;
        this.nombreTablaPaso = nombreTablaPaso;
        this.nombre = nombre;
        this.nombreClaseValidacion = nameEnfermedadFrond;
        this.nomTabFin = nomTabFin;
    }

    public ModelCmEnfermedades(int id, String nombreTablaPaso, Date fechaCreacion, boolean estado) {
        this.id = id;
        this.nombreTablaPaso = nombreTablaPaso;
        this.fechaCreacion=fechaCreacion;
        this.estado=estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreTablaPaso() {
        return nombreTablaPaso;
    }

    public void setNombreTablaPaso(String nombreTablaPaso) {
        this.nombreTablaPaso = nombreTablaPaso;
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

    public String getNombreClaseValidacion() {
        return nombreClaseValidacion;
    }

    public void setNombreClaseValidacion(String nombreClaseValidacion) {
        this.nombreClaseValidacion = nombreClaseValidacion;
    }

    public String getNomTabFin() {
        return nomTabFin;
    }

    public void setNomTabFin(String nomTabFin) {
        this.nomTabFin = nomTabFin;
    }

    public String getNovedades() {
        return novedades;
    }

    public void setNovedades(String novedades) {
        this.novedades = novedades;
    }

    @Override
    public String toString() {
        return "entity.ReadCmEnfermedades[ id=" + id + " ]";
    }
    
}
