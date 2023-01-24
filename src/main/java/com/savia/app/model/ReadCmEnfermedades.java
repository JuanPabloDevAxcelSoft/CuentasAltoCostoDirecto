/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.savia.app.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Angel Gonzalez
 */
@Entity
@Table(name = "cm_enfermedades")
public class ReadCmEnfermedades implements Serializable {

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
    private String nombre;
    private Boolean estado;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre_clase_validacion ")
    private String nombreClaseValidacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nom_tab_fin")
    private String nomTabFin;


    public ReadCmEnfermedades() {
    }

    public ReadCmEnfermedades(Integer id) {
        this.id = id;
    }

    public ReadCmEnfermedades(Integer id, String nombreTabla, String nombre, String nombreClaseValidacion, String nomTabFin) {
        this.id = id;
        this.nombreTabla = nombreTabla;
        this.nombre = nombre;
        this.nombreClaseValidacion = nombreClaseValidacion;
        this.nomTabFin = nomTabFin;
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


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReadCmEnfermedades)) {
            return false;
        }
        ReadCmEnfermedades other = (ReadCmEnfermedades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ReadCmEnfermedades[ id=" + id + " ]";
    }
    
}
