package com.savia.hemofilia.service.impl;

import com.savia.hemofilia.model.IpsWriteModel;
import com.savia.hemofilia.repository.IpsWriteRepository;
import com.savia.hemofilia.service.IpsWriteService;
import com.savia.hemofilia.valueobject.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IpsWriteServiceImpl implements IpsWriteService {

    @Autowired
    IpsWriteRepository ipsWriteRepository;

    @Override
    public ResponseEntity<Message> deleteIps(int idIps) {
        try {
            ipsWriteRepository.deleteById(idIps);
            return ResponseEntity.ok()
                    .body(new Message("Se elimino la ips correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new Message("No se elimino la ips  : " + e.getLocalizedMessage()));
        }
    }

    @Override
    public ResponseEntity<Message> updateIps(int idIps, String nombreIps) {
        try {
            ipsWriteRepository.save(new IpsWriteModel(idIps, nombreIps.toLowerCase(), new Date(), true));
            return ResponseEntity.ok()
                    .body(new Message("Se actualizo la Ips correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new Message("No se cargo la enfermedad  : " + e.getLocalizedMessage()));
        }
    }

    @Override
    public ResponseEntity<Message> saveIps(String nombreIps) {
        try {
            ipsWriteRepository.save(new IpsWriteModel(nombreIps.toLowerCase(), new Date(), true));
            return ResponseEntity.ok()
                    .body(new Message("Se cargo la Ips correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new Message("No se cargo la enfermedad  : " + e.getLocalizedMessage()));
        }
    }

}
