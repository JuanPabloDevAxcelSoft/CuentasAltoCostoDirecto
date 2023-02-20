package com.savia.app.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pacientes {
    private Integer id;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String fechaNacimiento;
    private String sexo;
    private String codigoPerteneneciaEtnica;
    private String claveArchivo;
    private String novedades;
    private Long idDetalle;
    private Long idCargaEnfermedad;

    public Pacientes(Object objecto) {
        List<?> list = new ArrayList<>();
        if (objecto.getClass().isArray()) {
            list = Arrays.asList((Object[]) objecto);
            this.id = Integer.parseInt(list.get(0).toString());
            this.primerNombre = list.get(2).toString();
            this.segundoNombre = list.get(3).toString();
            this.primerApellido = list.get(4).toString();
            this.segundoApellido = list.get(5).toString();
            this.tipoIdentificacion = list.get(6).toString();
            this.numeroIdentificacion = list.get(7).toString();
            this.fechaNacimiento = list.get(8).toString();
            this.sexo = list.get(9).toString();
            this.codigoPerteneneciaEtnica = list.get(10).toString();
            this.claveArchivo = list.get(11).toString();
            this.novedades = list.get(12).toString();
            this.idDetalle = Long.parseLong(list.get(13).toString());
            this.idCargaEnfermedad = Long.parseLong(list.get(14).toString());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCodigoPerteneneciaEtnica() {
        return codigoPerteneneciaEtnica;
    }

    public void setCodigoPerteneneciaEtnica(String codigoPerteneneciaEtnica) {
        this.codigoPerteneneciaEtnica = codigoPerteneneciaEtnica;
    }

    public String getClaveArchivo() {
        return claveArchivo;
    }

    public void setClaveArchivo(String claveArchivo) {
        this.claveArchivo = claveArchivo;
    }

    public String getNovedades() {
        return novedades;
    }

    public void setNovedades(String novedades) {
        this.novedades = novedades;
    }

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Long getIdCargaEnfermedad() {
        return idCargaEnfermedad;
    }

    public void setIdCargaEnfermedad(Long idCargaEnfermedad) {
        this.idCargaEnfermedad = idCargaEnfermedad;
    }

}
