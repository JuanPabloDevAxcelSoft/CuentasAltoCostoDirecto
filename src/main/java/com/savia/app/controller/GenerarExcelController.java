package com.savia.app.controller;

import com.savia.app.dto.PacienteExcelDto;
import com.savia.app.util.GenerarExcelApartirObjecto;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.savia.app.constants.KeySsEmitter;

@RestController
@RequestMapping(value = "/api/v1/excel")
public class GenerarExcelController {
    @Autowired
    GenerarExcelApartirObjecto generarExcelApartirObjecto;

    private final Logger logger = LoggerFactory.getLogger(GenerarExcelController.class);

    private List<SseEmitter> listSseEmitter = new CopyOnWriteArrayList<>();

    @RequestMapping("/generar")
    public SseEmitter getSubcribe() {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        String message = "";
        try {
            sseEmitter.send(SseEmitter.event().name(KeySsEmitter.KEY_INIT_GERERAR.toString()));
            message = "Archivo de excel en proceso";
        } catch (Exception e) {
            message = "Ocurrio un error interno al momento de generar excel : " + e.getMessage();
        }
        sseEmitter.onCompletion(() -> this.listSseEmitter.remove(sseEmitter));
        listSseEmitter.add(sseEmitter);
        this.logger.info(message);
        return sseEmitter;
    }


    @PostMapping(value = "/descargar", consumes = { "application/json" })
    public void getDispirarEvento(@RequestBody PacienteExcelDto pacienteExcelDto) throws InterruptedException {
        generarExcelApartirObjecto.getProcesoArchivoExcel(pacienteExcelDto, listSseEmitter);
    }

}
