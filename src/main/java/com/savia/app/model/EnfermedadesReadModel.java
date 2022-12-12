package com.savia.app.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_enfermedades", schema = "public")
@NamedQueries({
        @NamedQuery(name = "EnfermedadesReadModel.findAll", query = "SELECT i FROM EnfermedadesReadModel i") })
public class EnfermedadesReadModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "name_tables", unique = true)
    private String tabla;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creacion;
    private Boolean estado;

    public EnfermedadesReadModel() {
    }

    public EnfermedadesReadModel(Integer id) {
        this.id = id;
    }

    public EnfermedadesReadModel(Integer id, String nameTables) {
        this.id = id;
        this.tabla = nameTables;
    }

    public EnfermedadesReadModel(Integer id, String nameTables, Boolean estado) {
        this.id = id;
        this.tabla = nameTables;
        this.estado = estado;
    }

    public EnfermedadesReadModel(String nameTables, Date creacion, Boolean estado) {
        this.tabla = nameTables;
        this.creacion = creacion;
        this.estado = estado;
    }

    public EnfermedadesReadModel(Integer id, String nameTables, Date creacion, Boolean estado) {
        this.id = id;
        this.tabla = nameTables;
        this.creacion = creacion;
        this.estado = estado;
    }

    public EnfermedadesReadModel(String nameTables, Boolean estado) {
        this.tabla = nameTables;
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
        return creacion;
    }

    public void setFechaCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
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
        if (!(object instanceof EnfermedadesReadModel)) {
            return false;
        }
        EnfermedadesReadModel other = (EnfermedadesReadModel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "savia.mavenproject1.entity.EnfermedadesReadModel[ id=" + id + " ]";
    }
}
