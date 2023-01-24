package com.savia.app.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacientesConsulta {

    private Integer id;
    private String primer_nombre;
    private String segundo_nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private String tipo_identificacion;
    private String numero_identificacion;
    private String fecha_nacimiento;
    private String sexo;
    private String codigo_pertenencia_etnica;
    private Long id_detalle;
    private Long id_carga;

    public PacientesConsulta(Object object) {
        List<?> list = new ArrayList<>();
        if (object.getClass().isArray()) {
            list = Arrays.asList((Object[]) object);
            this.id = Integer.parseInt(list.get(0).toString());
            this.primer_nombre = list.get(1).toString();
            this.segundo_nombre = list.get(3).toString();
            this.primer_apellido = list.get(4).toString();
            this.segundo_apellido = list.get(5).toString();
            this.tipo_identificacion = list.get(6).toString();
            this.numero_identificacion = list.get(7).toString();
            this.fecha_nacimiento = list.get(8).toString();
            this.sexo = list.get(9).toString();
            this.codigo_pertenencia_etnica = list.get(10).toString();
            this.id_detalle = Long.parseLong(list.get(12).toString());
            this.id_carga = Long.parseLong(list.get(13).toString());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrimer_nombre() {
        return primer_nombre;
    }

    public void setPrimer_nombre(String primer_nombre) {
        this.primer_nombre = primer_nombre;
    }

    public String getSegundo_nombre() {
        return segundo_nombre;
    }

    public void setSegundo_nombre(String segundo_nombre) {
        this.segundo_nombre = segundo_nombre;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public String getTipo_identificacion() {
        return tipo_identificacion;
    }

    public void setTipo_identificacion(String tipo_identificacion) {
        this.tipo_identificacion = tipo_identificacion;
    }

    public String getNumero_identificacion() {
        return numero_identificacion;
    }

    public void setNumero_identificacion(String numero_identificacion) {
        this.numero_identificacion = numero_identificacion;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCodigo_pertenencia_etnica() {
        return codigo_pertenencia_etnica;
    }

    public void setCodigo_pertenencia_etnica(String codigo_pertenencia_etnica) {
        this.codigo_pertenencia_etnica = codigo_pertenencia_etnica;
    }

    public Long getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(Long id_detalle) {
        this.id_detalle = id_detalle;
    }

    public Long getId_carga() {
        return id_carga;
    }

    public void setId_carga(Long id_carga) {
        this.id_carga = id_carga;
    }

}
