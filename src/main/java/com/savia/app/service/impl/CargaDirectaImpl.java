package com.savia.app.service.impl;

import com.savia.app.constants.PathFileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.savia.app.dto.EnfermedadesReadDto;
import com.savia.app.service.CargaDirectaService;
import com.savia.app.service.EnfermedadesReadService;
import com.savia.app.vo.ResponseMessage;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Service
public class CargaDirectaImpl implements CargaDirectaService {
    private String folder = PathFileUpload.PATH_FILE_UPLOAD + "upload/";

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    EnfermedadesReadService enfermedadesServiceDirect;

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage> loadDataBaseDirect(String ruta, Integer idEnfermedad,
            String nombreArchivoOrig) {
        ResponseMessage response = new ResponseMessage();
        String message = "";
        HttpStatus status = HttpStatus.ACCEPTED;
        String claveArchivo = folder.replace("\\", "/");
        claveArchivo = ruta.replace(claveArchivo, "");
        claveArchivo = claveArchivo.replace(".csv", "");

        try {
            if ((!ruta.isEmpty()) && (idEnfermedad > 0)) {
                EnfermedadesReadDto enfermedadesReadDtoObj = enfermedadesServiceDirect.findEnfermedadById(idEnfermedad);
                if (enfermedadesReadDtoObj != null) {
                    String delimitador = this.getDelimiterArchivo(ruta);
                    String pureSql = "LOAD DATA LOCAL INFILE '" + ruta +
                            "' INTO TABLE " + enfermedadesReadDtoObj.getNameTables()
                            + " FIELDS TERMINATED BY '" + delimitador + "' LINES TERMINATED BY '\\n' IGNORE 1 ROWS SET clave_archivo='" + claveArchivo
                            + "' , id =0 , nombre_archivo_original='"
                            + nombreArchivoOrig + "';";
                    Query nativeQuery = entityManager.createNativeQuery(pureSql);
                    nativeQuery.executeUpdate();
                    status = HttpStatus.OK;
                    message = "El archivo fue cargado correctamente";
                } else {
                    message = "Id de la enfermedad no existe";
                    status = HttpStatus.NOT_FOUND;
                }
            } else {
                message = "Verifique los datos al momento de enviar, Datos no aceptados.";
                status = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception e) {
            message = "El archivo: tiene un error : " + e.getLocalizedMessage();
        }
        response.setMessage(message);
        response.setStatus(status);
        return ResponseEntity.ok().body(response);
    }

    private String getDelimiterArchivo(String ruta) {
        String delimitador = ";";
        try {
            BufferedReader bufferReadFile = new BufferedReader(new FileReader(ruta));
            String lecturaLineas = bufferReadFile.readLine();
            String partes[] = lecturaLineas.split(",");
            if (partes.length != 1) {
                delimitador = ",";
            }
            bufferReadFile.close();
        } catch (Exception e) {
            System.out.println("ocurrion un error al momento de realizar la lectura del archivo : " + e.getMessage());
        }
        return delimitador;
    }

}
