package com.savia.app.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.savia.app.constants.PathFileUpload;
import com.savia.app.dto.UploadDirectDto;
import com.savia.app.service.CargaDirectaService;
import com.savia.app.service.UploadService;
import com.savia.app.util.ResponseEntityJson;
import com.savia.app.valueobject.Message;

@RestController
@RequestMapping(value = "/documento/v1", produces = "application/json;charset=UTF-8")
public class CargaDirectaController {

    @Autowired
    private UploadService upload;

    @Autowired
    private CargaDirectaService cargaDirectaService;

    private ResponseEntityJson jsonResponse = new ResponseEntityJson();

    @PostMapping("/carga")
    public ResponseEntity<Message> upload(@RequestParam("file") MultipartFile file,
            @RequestParam("cuentaTipo") int cuentaTipo, @RequestParam("ipsEmisora") String ipsEmisora) {
        return (upload.save(file, cuentaTipo, ipsEmisora));
    }

    @PostMapping("/carga/bd")
    @ExceptionHandler({ SQLException.class, DataAccessException.class })
    public ResponseEntity<String> uploadDirec(@RequestBody UploadDirectDto uploadDirectDto) {
        String path = PathFileUpload.PATH_FILE_UPLOAD + uploadDirectDto.getRuta();
        ResponseEntity<String> response = null;
        try {
            response = (cargaDirectaService.loadDataBaseDirect(path, uploadDirectDto.getIdEnfermedad()));
        } catch (Exception e) {
            response = this.jsonResponse.ResponseHttp(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
        return response;
    }

}
