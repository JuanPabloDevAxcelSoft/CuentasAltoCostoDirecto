package com.savia.app.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ResponseMessage {
    private String message;
    private Integer status;
    private List<Object> data;
    private Object item;

    public ResponseMessage() {
        this.message = "";
        this.status = 204;
        this.data = new ArrayList<>();
        this.item = null;
    }

    public ResponseMessage(String message, HttpStatus status) {
        this.message = message;
        this.status = Integer.parseInt(status.toString().substring(0, 3));
        this.data = new ArrayList<>();
        this.item = null;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }

    public List<Object> getData() {
        return data;
    }

    public Object getItem() {
        return item;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(HttpStatus status) {
        this.status = Integer.parseInt(status.toString().substring(0, 3));
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public void setItem(Object item) {
        this.item = item;
    }

}
