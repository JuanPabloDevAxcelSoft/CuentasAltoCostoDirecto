package com.savia.app.components;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Value("${allowed.validation}")
    private String server;

    @PersistenceContext
    protected EntityManager entityManager;

    private final String PATH = PathFileUpload.PATH_FILE_UPLOAD + "upload\\";

    private Integer idEnfermedad = 1;
    private String nombreTabla = "cm_hemofilia_paso";

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

                        List<Object> resultCantidad = this.getCantidadValidar();
                        if (resultCantidad != null) {
                            for (Object valor : resultCantidad) {
                                this.callDirectService(valor, claveArchivo);
                            }
                        }
                    }
                } else {
                    System.out.println("Esperando por recibir archivos...");
                }
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error, no se puede procesar la informacion");
        }
    }

    private List<Object> getCantidadValidar() {
        List<Object> listResultante = null;
        String pureQuery = "SELECT tab.id";
        pureQuery += " FROM " + this.nombreTabla + " AS tab";
        pureQuery += " WHERE tab.campo_leido = :estado";
        try {
            Query query = this.entityManager.createNativeQuery(pureQuery);
            query.setParameter("estado", 0);
            listResultante = query.getResultList();
        } catch (Exception e) {
            this.LOGGER.info("Ocurrio un error : " + e.getMessage());
        }
        return listResultante;
    }

    public void callDirectService(Object id, String clave) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        String endpoint = "/api/v1/validacion?idPaciente=" + id + "&idEnfermedad=" + this.idEnfermedad + "&claveArchivo=" + clave.replace(".csv", "");
        String ruta = server + endpoint;

        try {

            HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
            ResponseEntity<String> responseEntity = rest.exchange(ruta, HttpMethod.GET, requestEntity, String.class);
            String response = responseEntity.getBody();
            this.LOGGER.info(response);
        } catch (Exception e) {
            this.LOGGER.info("Error al momento de realizar el llamado al servicio de directo : " + e.getMessage());
        }
    }
}
