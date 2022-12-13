package com.savia.app.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_enfermedades", schema = "public")
public class EnfermedadesWriteModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "name_tables", unique = true)
    private String nameTables;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    private Boolean estado;

    public EnfermedadesWriteModel() {
    }

    public EnfermedadesWriteModel(Integer id) {
        this.id = id;
    }

    public EnfermedadesWriteModel(Integer id, String nameTables) {
        this.id = id;
        this.nameTables = nameTables;
    }

    public EnfermedadesWriteModel(Integer id, String nameTables, Boolean estado) {
        this.id = id;
        this.nameTables = nameTables;
        this.estado = estado;
    }

    public EnfermedadesWriteModel(String nameTables, Date fechaCreacion, Boolean estado) {
        this.nameTables = nameTables;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public EnfermedadesWriteModel(Integer id, String nameTables, Date fechaCreacion, Boolean estado) {
        this.id = id;
        this.nameTables = nameTables;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public EnfermedadesWriteModel(String nameTables, Boolean estado) {
        this.nameTables = nameTables;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameTables() {
        return nameTables;
    }

    public void setNameTables(String nameTables) {
        this.nameTables = nameTables;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnfermedadesWriteModel)) {
            return false;
        }
        EnfermedadesWriteModel other = (EnfermedadesWriteModel) object;
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
