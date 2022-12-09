package com.savia.app.util;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;

public class ResponseEntityJson {

    private static final Logger LOG = Logger.getLogger(ResponseEntityJson.class.getName());

    private JSONObject JSONObject = null;

    /**
     * @param message: Mensaje de retorno al cliente
     * @param status:  Códgo de estado http
     * @param data:    Información a retornar al cliente, Si no retorna nada colocar
     *                 como parametro null
     * @return
     */
    public ResponseEntity<String> ResponseHttp(String message, HttpStatus status, Object data) {
        ResponseEntity<String> response = null;
        try {
            this.JSONObject = new JSONObject();
            JSONObject.put("message", message);
            JSONObject.put("status", status);
            if (data != null) {
                Gson gson = new Gson();
                String json = gson.toJson(data);
                JSONObject.put("data", (data instanceof List) ? new JSONArray(json) : new JSONObject(json));
                gson = null;
            }
            response = new ResponseEntity<String>(JSONObject.toString(), status);
        } catch (JSONException e) {
            LOG.log(Level.INFO, "Ocurrio un error al momento de generar el JSON : {0}", e.getLocalizedMessage());
        } catch (Exception e) {
            LOG.log(Level.INFO, "Ocurrio un error : {0}", e.getMessage());
        }
        return response;
    }
}
