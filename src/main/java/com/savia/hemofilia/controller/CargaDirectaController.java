package com.savia.hemofilia.controller;

import com.savia.hemofilia.service.CargaDirectaService;
import com.savia.hemofilia.service.UploadService;
import com.savia.hemofilia.valueobject.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/documento/v1")
public class CargaDirectaController {

    @Autowired
    private UploadService upload;

    @Autowired
    private CargaDirectaService cargaDirectaService;

    @PostMapping("/carga")
    public ResponseEntity<Message> upload(@RequestParam("file") MultipartFile file,
            @RequestParam("cuentaTipo") int cuentaTipo, @RequestParam("ipsEmisora") String ipsEmisora) {
        return (upload.save(file, cuentaTipo, ipsEmisora));
    }

    @PostMapping("/carga/bd")
    public ResponseEntity<Message> uploadDirec(@RequestParam("ruta") String ruta,
            @RequestParam("cuentaTipo") int cuentaTipo) {
        return (cargaDirectaService.loadDataBaseDirect(ruta, cuentaTipo));
    }
}
