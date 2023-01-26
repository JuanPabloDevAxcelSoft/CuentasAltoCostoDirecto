package com.savia.app.vo;

import java.util.ArrayList;
import org.springframework.http.HttpStatus;

public class ResponseJson {
    private String message;
    private Integer status;
    private ArrayList<Object> data;

    public ResponseJson() {
        this.message = "";
        this.status = 204;
        this.data = new ArrayList<Object>();
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
}
