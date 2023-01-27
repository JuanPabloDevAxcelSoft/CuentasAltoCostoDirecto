package com.savia.app.dto;

public class PacienteExcelDto {
    private int idEnfermedad;
    private int idIps;
    private boolean bandera;
    private String desde;
    private String hasta;

    public PacienteExcelDto(int idEnfermedad, int idIps, boolean bandera, String desde, String hasta) {
        this.idEnfermedad = idEnfermedad;
        this.idIps = idIps;
        this.bandera = bandera;
        this.desde = desde;
        this.hasta = hasta;
    }

    public int getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(int idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    public int getIdIps() {
        return idIps;
    }

    public void setIdIps(int idIps) {
        this.idIps = idIps;
    }

    public boolean isBandera() {
        return bandera;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }
}
