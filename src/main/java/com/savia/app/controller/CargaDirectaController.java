package com.savia.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.savia.app.dto.UploadDirectDto;
import com.savia.app.service.CargaDirectaService;
import com.savia.app.service.UploadService;
import com.savia.app.valueobject.Message;

@RestController
@RequestMapping(value = "/documento/v1", produces = "application/json;charset=UTF-8")
public class CargaDirectaController {

    @Autowired
    private UploadService upload;

    @Autowired
    private CargaDirectaService cargaDirectaService;

    /* Base de datos */
    private String path = "D:/FILESERVERTEST_LOCAL/";

    @PostMapping("/carga")
    public ResponseEntity<Message> upload(@RequestParam("file") MultipartFile file,
            @RequestParam("cuentaTipo") int cuentaTipo, @RequestParam("ipsEmisora") String ipsEmisora) {
        return (upload.save(file, cuentaTipo, ipsEmisora));
    }

    @PostMapping("/carga/bd")
    public ResponseEntity<Message> uploadDirec(@RequestBody UploadDirectDto uploadDirectDto) {
        path += uploadDirectDto.getRuta();
        return (cargaDirectaService.loadDataBaseDirect(path, uploadDirectDto.getIdEnfermedad()));
    }

    // @PostMapping("/test")
    // public ResponseEntity<Message> uploadDirec(@RequestBody UploadDirectDto
    // uploadDirectDto) {
    // System.out.println("Id : " + uploadDirectDto.getIdEnfermedad());
    // System.out.println("Ruta: " + uploadDirectDto.getRuta());
    // System.out.println("Id Ips: " + uploadDirectDto.getIdIps());
    // return null;
    // }

}
