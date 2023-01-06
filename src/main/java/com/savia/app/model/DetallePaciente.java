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

@Entity
@Table(name = "tbl_detalle_paciente")
public class DetallePaciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "regimen_afiliacion")
    private Character regimen_afiliacion;

    @Basic(optional = false)
    @NotNull
    @Column(name = "municipio_residencia")
    private int municipio_residencia;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 21)
    private String telefono;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "codigo_eapb")
    private String codigo_eapb;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_afilicion_eapb")
    @Temporal(TemporalType.DATE)
    private Date fecha_afilicion_eapb;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_muerte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_muerte;

    @Basic(optional = false)
    @NotNull
    @Column(name = "causa_muerte")
    private Character causa_muerte;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_corte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_corte;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo_serial")
    private String codigo_serial;

    @JoinColumn(name = "id_paciente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Paciente id_paciente;

    public DetallePaciente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getRegimenAfiliacion() {
        return regimen_afiliacion;
    }

    public void setRegimenAfiliacion(Character regimenAfiliacion) {
        this.regimen_afiliacion = regimenAfiliacion;
    }

    public int getMunicipioResidencia() {
        return municipio_residencia;
    }

    public void setMunicipioResidencia(int municipioResidencia) {
        this.municipio_residencia = municipioResidencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodigoEapb() {
        return codigo_eapb;
    }

    public void setCodigoEapb(String codigoEapb) {
        this.codigo_eapb = codigoEapb;
    }

    public Date getFechaAfilicionEapb() {
        return fecha_afilicion_eapb;
    }

    public void setFechaAfilicionEapb(Date fechaAfilicionEapb) {
        this.fecha_afilicion_eapb = fechaAfilicionEapb;
    }

    public Date getFechaMuerte() {
        return fecha_muerte;
    }

    public void setFechaMuerte(Date fechaMuerte) {
        this.fecha_muerte = fechaMuerte;
    }

    public Character getCausaMuerte() {
        return causa_muerte;
    }

    public void setCausaMuerte(Character causaMuerte) {
        this.causa_muerte = causaMuerte;
    }

    public Date getFechaCorte() {
        return fecha_corte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fecha_corte = fechaCorte;
    }

    public String getCodigoSerial() {
        return codigo_serial;
    }

    public void setCodigoSerial(String codigoSerial) {
        this.codigo_serial = codigoSerial;
    }

    public Paciente getIdPaciente() {
        return id_paciente;
    }

    public void setIdPaciente(Paciente idPaciente) {
        this.id_paciente = idPaciente;
    }

}
