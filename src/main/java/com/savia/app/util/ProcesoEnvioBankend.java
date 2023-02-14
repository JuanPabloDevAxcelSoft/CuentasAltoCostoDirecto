package com.savia.app.util;

import com.savia.app.components.TaskBackComponent;
import com.savia.app.constants.EnumNombreColumnasTablaCmEnfermedad;
import com.savia.app.service.EnfermedadesReadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class ProcesoEnvioBankend {
    private final Logger LOGGER = LoggerFactory.getLogger(TaskBackComponent.class);

    @Value("${allowed.validation}")
    private String server;

    @Autowired
    EnfermedadesReadService enfermedadesReadService;;
    private String nombreTabla;

    public void setTransferencia(int idEnfermedad, String claveArchivo) {
        try {
            nombreTabla = enfermedadesReadService.getNombreTablaGeneric(EnumNombreColumnasTablaCmEnfermedad.nombre_tabla_paso.toString(), idEnfermedad);
            List<Object> resultCantidad = enfermedadesReadService.getCantidadValidar(nombreTabla);
            if (resultCantidad != null) {
                for (Object valor : resultCantidad) {
                    callDirectService(valor, claveArchivo,idEnfermedad);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Ocurrio un error :" + e.getMessage());
        }

    }

    public void callDirectService(Object id, String clave,int idEnfermedad) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        String endpoint = "/api/v1/validacion?idPaciente=" + id + "&idEnfermedad=" + idEnfermedad + "&claveArchivo=" + clave.replace(".csv", "");
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
