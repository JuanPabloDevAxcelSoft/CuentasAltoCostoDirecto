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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Angel Gonzalez
 */
@Entity
@Table(name = "cm_detalle_paciente")
@XmlRootElement
public class CmDetallePaciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "regimen_afiliacion")
    private Character regimenAfiliacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "municipio_residencia")
    private int municipioResidencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 21)
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "codigo_eapb")
    private String codigoEapb;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_afilicion_eapb")
    @Temporal(TemporalType.DATE)
    private Date fechaAfilicionEapb;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_muerte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMuerte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "causa_muerte")
    private Character causaMuerte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_corte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCorte;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo_serial")
    private String codigoSerial;
    @JoinColumn(name = "id_paciente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CmPaciente idPaciente;

    public CmDetallePaciente() {
    }

    public CmDetallePaciente(Long id, Character regimenAfiliacion, int municipioResidencia, String telefono,
            String codigoEapb, Date fechaAfilicionEapb, Date fechaMuerte, Character causaMuerte, Date fechaCorte,
            String codigoSerial) {
        this.id = id;
        this.regimenAfiliacion = regimenAfiliacion;
        this.municipioResidencia = municipioResidencia;
        this.telefono = telefono;
        this.codigoEapb = codigoEapb;
        this.fechaAfilicionEapb = fechaAfilicionEapb;
        this.fechaMuerte = fechaMuerte;
        this.causaMuerte = causaMuerte;
        this.fechaCorte = fechaCorte;
        this.codigoSerial = codigoSerial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getRegimenAfiliacion() {
        return regimenAfiliacion;
    }

    public void setRegimenAfiliacion(Character regimenAfiliacion) {
        this.regimenAfiliacion = regimenAfiliacion;
    }

    public int getMunicipioResidencia() {
        return municipioResidencia;
    }

    public void setMunicipioResidencia(int municipioResidencia) {
        this.municipioResidencia = municipioResidencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodigoEapb() {
        return codigoEapb;
    }

    public void setCodigoEapb(String codigoEapb) {
        this.codigoEapb = codigoEapb;
    }

    public Date getFechaAfilicionEapb() {
        return fechaAfilicionEapb;
    }

    public void setFechaAfilicionEapb(Date fechaAfilicionEapb) {
        this.fechaAfilicionEapb = fechaAfilicionEapb;
    }

    public Date getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(Date fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }

    public Character getCausaMuerte() {
        return causaMuerte;
    }

    public void setCausaMuerte(Character causaMuerte) {
        this.causaMuerte = causaMuerte;
    }

    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public String getCodigoSerial() {
        return codigoSerial;
    }

    public void setCodigoSerial(String codigoSerial) {
        this.codigoSerial = codigoSerial;
    }

    public CmPaciente getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(CmPaciente idPaciente) {
        this.idPaciente = idPaciente;
    }
}
