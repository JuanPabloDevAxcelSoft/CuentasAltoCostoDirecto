package com.savia.app.controller;

import java.sql.SQLException;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.savia.app.service.UploadService;
import com.savia.app.vo.ResponseMessage;

@RestController
@RequestMapping(value = "/api/v1/documento", consumes = "multipart/form-data")
@MultipartConfig
public class CargaDirectaController {

    @Autowired
    private UploadService upload;

    @RequestMapping(value = "/carga", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @ExceptionHandler({ SQLException.class, DataAccessException.class })
    public ResponseEntity<ResponseMessage> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("idEnfermedad") int idEnfermedad,
            @RequestParam("idIps") int idIps) {

        ResponseEntity<ResponseMessage> response = (upload.save(file, idEnfermedad, idIps));
        return response;
    }
}
