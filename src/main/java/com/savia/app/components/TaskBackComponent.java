package com.savia.app.components;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.savia.app.constants.EnumNombreColumnasTablaCmEnfermedad;
import com.savia.app.service.EnfermedadesReadService;
import com.savia.app.util.EliminarFile;
import com.savia.app.util.ProcesoEnvioBankend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.savia.app.constants.PathFileUpload;

@SuppressWarnings("unchecked")
@Component
public class TaskBackComponent {

    private final Logger LOGGER = LoggerFactory.getLogger(TaskBackComponent.class);

    @Autowired
    EliminarFile eliminarFile;

    @Autowired
    EnfermedadesReadService enfermedadesReadService;

    @Autowired
    ProcesoEnvioBankend procesoEnvioBankend;

    private final String PATH = PathFileUpload.PATH_FILE_UPLOAD + "upload\\";

    @Async
    @Scheduled(fixedRate = 5000)
    public void setRealizarTarea() {
        try {
            File directorio = new File(this.PATH);
            File archivos[] = directorio.listFiles();
            if (archivos != null) {
                if (archivos.length != 0) {
                    for (File item : archivos) {
                        String claveArchivo = item.getName();
                        List<Integer>listId=enfermedadesReadService.getAllId();
                        for (Integer id:listId) {
                            procesoEnvioBankend.setTransferencia(id,claveArchivo);
                        }
                        eliminarFile.setRemoveFile(claveArchivo);
                    }
                } else {
                    System.out.println("Esperando por recibir archivos...");
                }
            }
        } catch (Exception e) {
            LOGGER.info("Ocurrio un error, no se puede procesar la informacion" +e.getMessage());
        }
    }




}
