package com.savia.app.dto;

public class ListarPacienteDto {
    private String claveArchivo;
    private int idEnfermedad;
    private int idIps;
    private int limit;
    private int page;
    private String desde;
    private String hasta;
    private String tipoDocumento;
    private String documento;

    private  String novedades;

    public ListarPacienteDto() {
    }

    public ListarPacienteDto(String claveArchivo, int idEnfermedad, int idIps, int limit, int page, String desde, String hasta, String tipoDocumento, String documento, String novedades) {
        this.claveArchivo = claveArchivo;
        this.idEnfermedad = idEnfermedad;
        this.idIps = idIps;
        this.limit = limit;
        this.page = page;
        this.desde = desde;
        this.hasta = hasta;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.novedades = novedades;
    }

    public String getClaveArchivo() {
        return claveArchivo;
    }

    public void setClaveArchivo(String claveArchivo) {
        this.claveArchivo = claveArchivo;
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

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNovedades() {
        return novedades;
    }

    public void setNovedades(String novedades) {
        this.novedades = novedades;
    }
}
