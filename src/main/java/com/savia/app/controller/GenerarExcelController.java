package com.savia.app.controller;

import com.savia.app.dto.PacienteExcelDto;
import com.savia.app.util.GenerarExcelApartirObjecto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.savia.app.constants.KeySsEmitter;

@RestController
@RequestMapping(value = "/api/v1/excel")
public class GenerarExcelController {
    @Autowired
    GenerarExcelApartirObjecto generarExcelApartirObjecto;

    private final Logger logger = LoggerFactory.getLogger(NotificacionesController.class);

    SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);

    @RequestMapping("/generar")
    public SseEmitter getSubcribe() {
        String message = "";
        try {
            this.sseEmitter.send(SseEmitter.event().name(KeySsEmitter.KEY_INIT_GERERAR.toString()));
            message = "Archivo de excel en proceso";
        } catch (Exception e) {
            message = "Ocurrio un error interno al momento de generar excel : " + e.getMessage();
            e.printStackTrace();
        }
        this.logger.info(message);
        return this.sseEmitter;
    }


    @RequestMapping(value = "/descargar", method = RequestMethod.POST, consumes = { "application/json" })
    public void getDispirarEvento(@RequestBody PacienteExcelDto pacienteExcelDto) throws InterruptedException {
        String messageLogger = "";
        /* CODIGO DE GENERAR EL EXCEL */
        generarExcelApartirObjecto.isExcel(pacienteExcelDto);
        /*
        for (int i = 0; i < 100; i++) {
            try {
                sseEmitter.send(SseEmitter.event().name(KeySsEmitter.KEY_PROCESS_GERERAR.toString()).data(i));
                messageLogger = "Notificacion enviada al usuario subscrito";
            } catch (Exception e) {
                messageLogger = "El emitter fue removido de la lista de subcritos";
            }
            this.logger.info(messageLogger);
            Thread.sleep(1000);
        }*/
    }

}
