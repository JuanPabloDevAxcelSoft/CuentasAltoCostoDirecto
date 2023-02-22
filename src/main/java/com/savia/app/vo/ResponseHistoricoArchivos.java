package com.savia.app.vo;

import com.savia.app.dto.HistoricoArchivoDto;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ResponseHistoricoArchivos {
    private String message;
    private Integer status;
    private List<HistoricoArchivoDto> data;
    private Object item;

    public ResponseHistoricoArchivos() {
        this.message = "";
        this.status = 204;
        this.data = new ArrayList<>();
        this.item = null;
    }

    public ResponseHistoricoArchivos(String message, HttpStatus status) {
        this.message = message;
        this.status = Integer.parseInt(status.toString().substring(0, 3));
        this.data = new ArrayList<>();
        this.item = null;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<HistoricoArchivoDto> getData() {
        return data;
    }

    public void setData(List<HistoricoArchivoDto> data) {
        this.data = data;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }
}
