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
public class WriteCmEnfermedades implements Serializable {

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
    @Column(name = "name_enfermedad_frond")
    private String nameEnfermedadFrond;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name_tables")
    private String nameTables;


    public WriteCmEnfermedades() {
    }

    public WriteCmEnfermedades(Integer id) {
        this.id = id;
    }

    public WriteCmEnfermedades(Integer id, String nombreTabla, String nombre, String nameEnfermedadFrond, String nameTables) {
        this.id = id;
        this.nombreTabla = nombreTabla;
        this.nombre = nombre;
        this.nameEnfermedadFrond = nameEnfermedadFrond;
        this.nameTables = nameTables;
    }

    public WriteCmEnfermedades(int id, String nombreTabla, Date fechaCreacion, boolean estado) {
        this.id = id;
        this.nombreTabla = nombreTabla;
        this.fechaCreacion=fechaCreacion;
        this.estado=estado;
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

    public String getNameEnfermedadFrond() {
        return nameEnfermedadFrond;
    }

    public void setNameEnfermedadFrond(String nameEnfermedadFrond) {
        this.nameEnfermedadFrond = nameEnfermedadFrond;
    }

    public String getNameTables() {
        return nameTables;
    }

    public void setNameTables(String nameTables) {
        this.nameTables = nameTables;
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
        if (!(object instanceof WriteCmEnfermedades)) {
            return false;
        }
        WriteCmEnfermedades other = (WriteCmEnfermedades) object;
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
