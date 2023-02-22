package com.savia.app.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistoricoArchivoDto {
    private  String claveArchivo;
    private String nombreArchivo;
    private String fechaCargue;

    public HistoricoArchivoDto(Object objecto) {
        List<?> list = new ArrayList<>();
        if (objecto.getClass().isArray()) {
            list = Arrays.asList((Object[]) objecto);
            this.claveArchivo = list.get(0).toString();
            this.nombreArchivo = list.get(1).toString();
            this.fechaCargue = list.get(2).toString();
        }
    }

    public String getClaveArchivo() {
        return claveArchivo;
    }

    public void setClaveArchivo(String claveArchivo) {
        this.claveArchivo = claveArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getFechaCargue() {
        return fechaCargue;
    }

    public void setFechaCargue(String fechaCargue) {
        this.fechaCargue = fechaCargue;
    }
}
