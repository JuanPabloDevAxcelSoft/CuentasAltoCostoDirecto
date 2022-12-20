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

@Entity
@Table(name = "tbl_paciente", schema = "public")
public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long id;

    @Size(max = 20)
    @Column(name = "primer_nombre")
    private String primer_nombre;

    @Size(max = 30)
    @Column(name = "segundo_nombre")
    private String segundo_nombre;

    @Size(max = 20)
    @Column(name = "primer_apellido")
    private String primer_apellido;

    @Size(max = 30)
    @Column(name = "segundo_apellido")
    private String segundo_apellido;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "tipo_identificacion")
    private String tipo_identificacion;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "numero_identificacion")
    private String numero_identificacion;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fecha_nacimiento;

    private Character sexo;

    @Column(name = "codigo_pertenencia_etnica")
    private Character codigo_pertenencia_etnica;

    // @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaciente")
    // private List<DetallePaciente> detalle_paciente;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimer_nombre() {
        return this.primer_nombre;
    }

    public void setPrimer_nombre(String primer_nombre) {
        this.primer_nombre = primer_nombre;
    }

    public String getSegundo_nombre() {
        return this.segundo_nombre;
    }

    public void setSegundo_nombre(String segundo_nombre) {
        this.segundo_nombre = segundo_nombre;
    }

    public String getPrimer_apellido() {
        return this.primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return this.segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public String getTipo_identificacion() {
        return this.tipo_identificacion;
    }

    public void setTipo_identificacion(String tipo_identificacion) {
        this.tipo_identificacion = tipo_identificacion;
    }

    public String getNumero_identificacion() {
        return this.numero_identificacion;
    }

    public void setNumero_identificacion(String numero_identificacion) {
        this.numero_identificacion = numero_identificacion;
    }

    public Date getFecha_nacimiento() {
        return this.fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Character getSexo() {
        return this.sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Character getCodigo_pertenencia_etnica() {
        return this.codigo_pertenencia_etnica;
    }

    public void setCodigo_pertenencia_etnica(Character codigo_pertenencia_etnica) {
        this.codigo_pertenencia_etnica = codigo_pertenencia_etnica;
    }

    // public List<DetallePaciente> getTblDetallePacienteList() {
    // return this.detalle_paciente;
    // }

    // public void setTblDetallePacienteList(List<DetallePaciente> detalle) {
    // this.detalle_paciente = detalle;
    // }

}
