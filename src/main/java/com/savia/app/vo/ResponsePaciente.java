package com.savia.app.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import com.savia.app.model.PacientesConsulta;

public class ResponsePaciente {
    private String message;
    private Integer status;
    private List<PacientesConsulta> data;

    public ResponsePaciente() {
        this.message = "";
        this.status = 204;
        this.data = new ArrayList<PacientesConsulta>();
    }

    public ResponsePaciente(String message, HttpStatus status) {
        this.message = message;
        this.status = Integer.parseInt(status.toString().substring(0, 3));
        this.data = new ArrayList<PacientesConsulta>();
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }

    public List<PacientesConsulta> getData() {
        return data;
    }
    public void setData(List<PacientesConsulta> data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(HttpStatus status) {
        this.status = Integer.parseInt(status.toString().substring(0, 3));
    }
}
