package com.savia.app.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.savia.app.constants.KeySsEmitter;

@RestController
@RequestMapping(path = "/api/v1/notificacion")
public class NotificacionesController {

    private final Logger logger = LoggerFactory.getLogger(NotificacionesController.class);

    private List<SseEmitter> listSseEmitter = new CopyOnWriteArrayList<>();

    /* Metodo para subcribir a un usuario */
    @RequestMapping("/subcribir")
    public SseEmitter getSubcribe() {
        String message = "";
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        try {
            sseEmitter.send(SseEmitter.event().name(KeySsEmitter.KEY_INIT.toString()));
            message = "Usuario subscrito";
        } catch (Exception e) {
            message = "Ocurrio un error interno : " + e.getMessage();
        }
        sseEmitter.onCompletion(() -> this.listSseEmitter.remove(sseEmitter));
        listSseEmitter.add(sseEmitter);
        this.logger.info(message);
        return sseEmitter;
    }

    /* Metodo para disparar un evento a todos los clientes subcritos */
    @PostMapping(value = "/evento", consumes = { "application/json" })
    public void getDispirarEvento(@RequestParam("message") String message) {

        String messageLogger = "";
        for (SseEmitter emitter : this.listSseEmitter) {
            try {
                emitter.send(SseEmitter.event().name(KeySsEmitter.KEY_ULTIMAS_NOTICIAS.toString()).data(message));
                messageLogger = "Notificacion enviada al usuario subscrito";
            } catch (Exception e) {
                this.listSseEmitter.remove(emitter);
                messageLogger = "El emitter fue removido de la lista de subcritos";
            }
        }
        this.logger.info(messageLogger);
    }
}
