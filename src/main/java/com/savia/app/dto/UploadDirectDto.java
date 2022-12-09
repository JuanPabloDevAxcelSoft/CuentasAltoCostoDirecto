package com.savia.app.dto;

import java.io.Serializable;

public class UploadDirectDto implements Serializable {

    private String ruta;
    private Integer idEnfermedad;
    private Integer idIps;

    public String getRuta() {
        return ruta;
    }

    public Integer getIdEnfermedad() {
        return idEnfermedad;
    }

    public Integer getIdIps() {
        return idIps;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public void setIdEnfermedad(Integer idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    public void setIdIps(Integer idIps) {
        this.idIps = idIps;
    }

}
