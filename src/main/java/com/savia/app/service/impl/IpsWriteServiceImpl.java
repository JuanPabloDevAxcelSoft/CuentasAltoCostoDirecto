package com.savia.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.savia.app.model.IpsWriteModel;
import com.savia.app.repository.IpsWriteRepository;
import com.savia.app.service.IpsWriteService;
import com.savia.app.util.ResponseEntityJson;

import java.util.Date;

@Service
public class IpsWriteServiceImpl implements IpsWriteService {

    @Autowired
    IpsWriteRepository ipsWriteRepository;

    @Override
    public ResponseEntity<String> deleteIps(int idIps) {
        ResponseEntityJson jsonResponse = new ResponseEntityJson();
        String message = "";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            if (idIps > 0) {
                ipsWriteRepository.deleteById(idIps);
            }
            message = (idIps > 0) ? "Se elimino la ips correctamente" : "El id enviado no es aceptado";
            status = (idIps > 0) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            message = "Ocurrio un error por tanto no se pudo eliminar la Ips : " + e.getLocalizedMessage();
        }
        return jsonResponse.ResponseHttp(message, status, null);
    }

    @Override
    public ResponseEntity<String> updateIps(int idIps, String nombreIps) {
        ResponseEntityJson jsonResponse = new ResponseEntityJson();
        String message = "";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            if ((idIps > 0) && (!nombreIps.isEmpty())) {
                ipsWriteRepository.save(new IpsWriteModel(idIps, nombreIps.toLowerCase(), new Date(), true));
            }
            message = ((idIps > 0) && (!nombreIps.isEmpty())) ? "Se actualizo la Ips correctamente"
                    : "Los datos enviado no son aceptados";
            status = ((idIps > 0) && (!nombreIps.isEmpty())) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            message = "Ocurrio un error al momento de actualizar la Ips  : " + e.getLocalizedMessage();
        }
        return jsonResponse.ResponseHttp(message, status, null);
    }

    @Override
    public ResponseEntity<String> saveIps(String nombreIps) {
        ResponseEntityJson jsonResponse = new ResponseEntityJson();
        String message = "";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            if (!nombreIps.isEmpty()) {
                ipsWriteRepository.save(new IpsWriteModel(nombreIps.toLowerCase(), new Date(), true));
            }
            message = (!nombreIps.isEmpty()) ? "Se cargo la Ips correctamente" : "Los datos enviado no son aceptados";
            status = (!nombreIps.isEmpty()) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            message = "Ocurrio un error al momento de guardar la Ipss : " + e.getLocalizedMessage();
        }
        return jsonResponse.ResponseHttp(message, status, null);
    }
}
