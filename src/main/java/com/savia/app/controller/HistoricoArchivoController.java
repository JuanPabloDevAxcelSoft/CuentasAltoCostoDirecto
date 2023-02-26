package com.savia.app.controller;

import com.savia.app.service.HistoricoArchivoService;
import com.savia.app.vo.ResponseHistoricoArchivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class HistoricoArchivoController {
    @Autowired
    HistoricoArchivoService historicoArchivoService;
    @GetMapping("/historico/archivo")
    public ResponseEntity<ResponseHistoricoArchivos> getHistoricoArchivo(@RequestParam("idEnfermedad") int idEnfermedad
    ,@RequestParam("limit")int limit,@RequestParam("page")  int page){
        return historicoArchivoService.getHistoricoArchivo(idEnfermedad,limit,page);
    }
}
