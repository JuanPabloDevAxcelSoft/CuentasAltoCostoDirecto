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
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Angel Gonzalez
 */
@Entity
@Table(name = "cm_paciente")
@XmlRootElement
public class CmPaciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long id;
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

    public CmPaciente(Long id, String tipoIdentificacion, String numeroIdentificacion) {
        this.id = id;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("primer_nombre")
    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    @JsonProperty("segundo_nombre")
    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    @JsonProperty("primer_apellido")
    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    @JsonProperty("segundo_apellido")
    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    @JsonProperty("tipo_identificacion")
    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    @JsonProperty("numero_identificacion")
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    @JsonProperty("fecha_nacimiento")
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

    @JsonProperty("codigo_pertenencia_etnica")
    public Character getCodigoPertenenciaEtnica() {
        return codigoPertenenciaEtnica;
    }

    public void setCodigoPertenenciaEtnica(Character codigoPertenenciaEtnica) {
        this.codigoPertenenciaEtnica = codigoPertenenciaEtnica;
    }
}
