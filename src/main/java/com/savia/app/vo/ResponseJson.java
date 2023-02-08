package com.savia.app.vo;

import java.util.ArrayList;
import org.springframework.http.HttpStatus;

public class ResponseJson {
    private String message;
    private Integer status;
    private ArrayList<Object> data;
    private String items;

    public ResponseJson() {
        this.message = "";
        this.status = 204;
        this.data = new ArrayList<Object>();
        this.items="";
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }

    public ArrayList<Object> getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(HttpStatus status) {
        this.status = Integer.parseInt(status.toString().substring(0, 3));
    }

    public void setData(ArrayList<Object> data) {
        this.data = data;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
}
