package com.savia.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.savia.app.model.IpsWriteModel;
import com.savia.app.repository.IpsWriteRepository;
import com.savia.app.service.IpsWriteService;
import com.savia.app.vo.ResponseMessage;

import java.util.Date;

@Service
public class IpsWriteServiceImpl implements IpsWriteService {

    @Autowired
    IpsWriteRepository ipsWriteRepository;

    @Override
    public ResponseEntity<ResponseMessage> deleteIps(int idIps) {
        ResponseMessage response = new ResponseMessage();
        try {
            if (idIps > 0) {
                ipsWriteRepository.deleteById(idIps);
            }
            response.setMessage((idIps > 0) ? "Se elimino la ips correctamente" : "El id enviado no es aceptado");
            response.setStatus((idIps > 0) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setMessage("Ocurrio un error por tanto no se pudo eliminar la Ips : " + e.getLocalizedMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<ResponseMessage> updateIps(int idIps, String nombreIps) {
        ResponseMessage response = new ResponseMessage();
        try {
            if ((idIps > 0) && (!nombreIps.isEmpty())) {
                ipsWriteRepository.save(new IpsWriteModel(idIps, nombreIps.toLowerCase(), new Date(), true));
            }
            response.setMessage(((idIps > 0) && (!nombreIps.isEmpty())) ? "Se actualizo la Ips correctamente"
                    : "Los datos enviado no son aceptados");
            response.setStatus(((idIps > 0) && (!nombreIps.isEmpty())) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setMessage("Ocurrio un error al momento de actualizar la Ips  : " + e.getLocalizedMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<ResponseMessage> saveIps(String nombreIps) {
        ResponseMessage response = new ResponseMessage();
        try {
            if (!nombreIps.isEmpty()) {
                ipsWriteRepository.save(new IpsWriteModel(nombreIps.toLowerCase(), new Date(), true));
            }
            response.setMessage(
                    (!nombreIps.isEmpty()) ? "Se cargo la Ips correctamente" : "Los datos enviado no son aceptados");
            response.setStatus((!nombreIps.isEmpty()) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setMessage("Ocurrio un error al momento de guardar la Ipss : " + e.getLocalizedMessage());
        }
        return ResponseEntity.ok().body(response);
    }
}
