package com.savia.app.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.savia.app.dto.Pacientes;

public class ResponsePaciente {
    private String message;
    private Integer status;
    private List<Pacientes> data;

    
    public ResponsePaciente() {
        this.message = "";
        this.status = 204;
        this.data = new ArrayList<Pacientes>();
    }

    public ResponsePaciente(String message, HttpStatus status) {
        this.message = message;
        this.status = Integer.parseInt(status.toString().substring(0, 3));
        this.data = new ArrayList<Pacientes>();
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }

    public List<Pacientes> getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(HttpStatus status) {
        this.status = Integer.parseInt(status.toString().substring(0, 3));
    }

    public void setData(List<Pacientes> data) {
        this.data = data;
    }
}
