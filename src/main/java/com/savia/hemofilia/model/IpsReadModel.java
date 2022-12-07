package com.savia.hemofilia.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ips", schema = "public")
public class IpsReadModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "name_ips", unique = true)
    private String nameIps;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    private Boolean estado;

    public IpsReadModel() {
    }

    public IpsReadModel(Integer id) {
        this.id = id;
    }

    public IpsReadModel(Integer id, String nameIps) {
        this.id = id;
        this.nameIps = nameIps;
    }

    public IpsReadModel(Integer id, String nameIps, Boolean estado) {
        this.id = id;
        this.nameIps = nameIps;
        this.estado = estado;
    }

    public IpsReadModel(String nameIps, Date fechaCreacion, Boolean estado) {
        this.nameIps = nameIps;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public IpsReadModel(Integer id, String nameIps, Date fechaCreacion, Boolean estado) {
        this.id = id;
        this.nameIps = nameIps;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public IpsReadModel(String nameIps, Boolean estado) {
        this.nameIps = nameIps;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IpsReadModel)) {
            return false;
        }
        IpsReadModel other = (IpsReadModel) object;
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
