package com.savia.app.controller;

import java.sql.SQLException;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.savia.app.constants.PathFileUpload;
import com.savia.app.dto.UploadDirectDto;
import com.savia.app.service.CargaDirectaService;
import com.savia.app.service.UploadService;
import com.savia.app.util.ResponseEntityJson;
import com.savia.app.valueobject.Message;

@RestController
@RequestMapping(value = "/api/v1/documento", consumes = "multipart/form-data")
@MultipartConfig
public class CargaDirectaController {

    @Autowired
    private UploadService upload;

    @Autowired
    private CargaDirectaService cargaDirectaService;

    private ResponseEntityJson jsonResponse = new ResponseEntityJson();

    @RequestMapping(value = "/carga", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public ResponseEntity<Message> upload(@RequestParam("file") MultipartFile file,
            @RequestParam("idEnfermedad") int idEnfermedad, @RequestParam("idIps") int idIps) {
        return (upload.save(file, idEnfermedad, idIps));
    }

    // @RequestMapping(value = "/carga", method = RequestMethod.POST, consumes = {
    // "multipart/form-data" })
    // public ResponseEntity<Message> upload(@RequestParam("file") MultipartFile
    // file) {
    // System.out.println(file);

    // String tempDir = System.getProperty("java.io.tmpdir");
    // if (!tempDir.endsWith("/") && !tempDir.endsWith("\\")) {
    // System.out.println("Entrada a la funcion!!");
    // tempDir = tempDir + "/";
    // }
    // return null;
    // }

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
