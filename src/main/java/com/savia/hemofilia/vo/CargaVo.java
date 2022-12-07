package com.savia.hemofilia.vo;

import java.io.Serializable;

public class CargaVo implements Serializable {
    // private String ruta;
    private Integer id;

    // public String getRuta() {
    // return ruta;
    // }

    public Integer getIdEnfermedad() {
        return id;
    }

    // public void setRuta(String ruta) {
    // this.ruta = ruta;
    // }

    public void setIdEnfermedad(Integer idEnfermedad) {
        this.id = idEnfermedad;
    }

}
