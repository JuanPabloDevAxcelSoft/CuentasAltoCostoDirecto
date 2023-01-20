<<<<<<< HEAD
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
>>>>>>> juan.dev
package com.savia.app.model;

import java.io.Serializable;
import java.util.Date;
<<<<<<< HEAD
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
=======
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
>>>>>>> juan.dev

/**
 *
 * @author Angel Gonzalez
 */
@Entity
@Table(name = "cm_paciente")
<<<<<<< HEAD
@XmlRootElement
=======
>>>>>>> juan.dev
public class CmPaciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long id;
<<<<<<< HEAD
=======
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
>>>>>>> juan.dev
    @Size(max = 20)
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Size(max = 30)
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Size(max = 20)
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Size(max = 30)
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "tipo_identificacion")
    private String tipoIdentificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "numero_identificacion")
    private String numeroIdentificacion;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    private Character sexo;
    @Column(name = "codigo_pertenencia_etnica")
    private Character codigoPertenenciaEtnica;

    public CmPaciente() {
    }

<<<<<<< HEAD
=======
    public CmPaciente(Long id) {
        this.id = id;
    }

>>>>>>> juan.dev
    public CmPaciente(Long id, String tipoIdentificacion, String numeroIdentificacion) {
        this.id = id;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
    }

<<<<<<< HEAD
=======
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

>>>>>>> juan.dev
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

<<<<<<< HEAD
    @JsonProperty("primer_nombre")
=======
>>>>>>> juan.dev
    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

<<<<<<< HEAD
    @JsonProperty("segundo_nombre")
=======
>>>>>>> juan.dev
    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

<<<<<<< HEAD
    @JsonProperty("primer_apellido")
=======
>>>>>>> juan.dev
    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

<<<<<<< HEAD
    @JsonProperty("segundo_apellido")
=======
>>>>>>> juan.dev
    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

<<<<<<< HEAD
    @JsonProperty("tipo_identificacion")
=======
>>>>>>> juan.dev
    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

<<<<<<< HEAD
    @JsonProperty("numero_identificacion")
=======
>>>>>>> juan.dev
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

<<<<<<< HEAD
    @JsonProperty("fecha_nacimiento")
=======
>>>>>>> juan.dev
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

<<<<<<< HEAD
    @JsonProperty("codigo_pertenencia_etnica")
=======
>>>>>>> juan.dev
    public Character getCodigoPertenenciaEtnica() {
        return codigoPertenenciaEtnica;
    }

    public void setCodigoPertenenciaEtnica(Character codigoPertenenciaEtnica) {
        this.codigoPertenenciaEtnica = codigoPertenenciaEtnica;
    }
<<<<<<< HEAD
=======

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CmPaciente)) {
            return false;
        }
        CmPaciente other = (CmPaciente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CmPaciente[ id=" + id + " ]";
    }

>>>>>>> juan.dev
}
