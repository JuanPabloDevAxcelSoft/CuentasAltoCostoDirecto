package com.savia.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

// import com.savia.app.model.DetallePaciente;
import com.savia.app.model.CmPaciente;
import com.savia.app.repository.DetallePacienteRepository;
import com.savia.app.service.DetallePacienteService;
import com.savia.app.vo.ResponseMessage;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class DetallePacienteImpl implements DetallePacienteService {

    @Autowired
    private DetallePacienteRepository detallePacienteRepository;

    @Override
    public ResponseEntity<ResponseMessage> getAllPacientePaginated() {
        ResponseMessage response = new ResponseMessage();
        List<CmPaciente> list = new ArrayList<>();
        try {
            list = detallePacienteRepository.findAll();
            response.setMessage((list.isEmpty()) ? "No hay registros para mostrar"
                    : "Cantidad de resultados encontrados : " + list.size());
            response.setStatus((list.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
            response.setData(Arrays.asList(list.toArray()));
        } catch (Exception e) {
            response.setMessage("Ocurrio un error : " + e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<ResponseMessage> getDetallePaciente(Long id) {
        ResponseMessage response = new ResponseMessage();
        try {
            // List<DetallePaciente> list = detallePacienteRepository.findByIdPaciente(id);
            // response.setMessage((!list.isEmpty()) ? ("Detalla del paciente con el id " +
            // id)
            // : "No se encontro registro de detalles con el valor enviado");
            // response.setStatus((!list.isEmpty()) ? HttpStatus.OK : HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.setMessage("Ocurrio un error al momento de consultar el detalle : " + e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

}