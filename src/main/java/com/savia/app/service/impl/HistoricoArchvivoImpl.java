package com.savia.app.service.impl;

import com.savia.app.consultas.ConsultasHistoricoArchivo;
import com.savia.app.dto.HistoricoArchivoDto;
import com.savia.app.service.HistoricoArchivoService;
import com.savia.app.util.ConvertListArrayToJson;
import com.savia.app.vo.ResponseHistoricoArchivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class HistoricoArchvivoImpl implements HistoricoArchivoService {
    @Autowired
    ConsultasHistoricoArchivo consultasHistoricoArchivo;
    @Autowired
    private ConvertListArrayToJson convertListArrayToJson;

    @Override
    public ResponseEntity<ResponseHistoricoArchivos> getHistoricoArchivo(int idEnfermedad,int limt, int page) {
        ResponseHistoricoArchivos response = new ResponseHistoricoArchivos();
        try {
                String numeroDeArchivos= consultasHistoricoArchivo.getHistoricoArchivo(idEnfermedad,true,limt,page).toString();
                List<Object> archvosObjetos = consultasHistoricoArchivo.getHistoricoArchivo(idEnfermedad,false, limt, page);
                List<HistoricoArchivoDto> historicoArchivoDtos= convertListArrayToJson.setConvertListObjectHistoricoArchivo(archvosObjetos);
                response.setData(historicoArchivoDtos);
                response.setItem(numeroDeArchivos);
                response.setMessage("Historico Archivo");
        } catch (NoSuchElementException e) {
            response.setMessage("Ocurrio un error al momento de realizar la consulta : " + e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }
}