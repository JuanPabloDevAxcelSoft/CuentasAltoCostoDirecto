package com.savia.hemofilia.controller;

import com.savia.hemofilia.service.CargaDirectaService;
import com.savia.hemofilia.service.UploadService;
import com.savia.hemofilia.valueobject.Message;
import com.savia.hemofilia.vo.CargaVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    // @RequestMapping(method = RequestMethod.POST, path = "/carga/bd", consumes =
    // "application/json", produces = "application/json")
    @PostMapping("/carga/bd")
    public ResponseEntity<Message> uploadDirec(@RequestParam("id") int id) {

        // System.out.println("Ruta: " + carga.getRuta());
        // System.out.println("ID: " + carga.getIdEnfermedad());

        return (cargaDirectaService.loadDataBaseDirect(
                "C:\\Users\\Angel Gonzalez\\Desktop\\FILESERVERTEST_LOCAL\\Hemofilia_Estructura_Reporte_2022_Direct.csv",
                id));
    }
}
