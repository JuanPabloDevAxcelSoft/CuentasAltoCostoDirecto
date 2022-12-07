package com.savia.hemofilia.service.impl;

import com.savia.hemofilia.model.EnfermedadesWriteModel;
import com.savia.hemofilia.repository.EnfermedadesWriteRepository;
import com.savia.hemofilia.service.EnfermedadesWriteService;
import com.savia.hemofilia.valueobject.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class EnfermedadesWriteServiceImpl implements EnfermedadesWriteService {
    @Autowired
    EnfermedadesWriteRepository enfermedadesRepository;

    @Override
    public ResponseEntity<Message> deleteEnfermedad(int idEnfermedad) {
        try {
            enfermedadesRepository.deleteById(idEnfermedad);
            return ResponseEntity.ok()
                    .body(new Message("Se elimino la enfermedad correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new Message("No se elimino la enfermedad  : " + e.getLocalizedMessage()));
        }
    }

    @Override
    public ResponseEntity<Message> updateEnfermedad(int idEnfermedad, String nombreEnfermedad) {
        try {
            enfermedadesRepository.save(new EnfermedadesWriteModel(idEnfermedad,
                    "tbl_" + nombreEnfermedad.toLowerCase() + "_paso", new Date(), true));
            return ResponseEntity.ok()
                    .body(new Message("Se cargo la enfermedad correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new Message("No se cargo la enfermedad  : " + e.getLocalizedMessage()));
        }
    }

    @Override
    public ResponseEntity<Message> saveEnfermedad(String nombreEnfermedad) {
        try {

            enfermedadesRepository.save(
                    new EnfermedadesWriteModel("tbl_" + nombreEnfermedad.toLowerCase() + "_paso", new Date(), true));
            return ResponseEntity.ok()
                    .body(new Message("Se cargo la enfermedad correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new Message("No se cargo la enfermedad  : " + e.getLocalizedMessage()));
        }
    }

}
