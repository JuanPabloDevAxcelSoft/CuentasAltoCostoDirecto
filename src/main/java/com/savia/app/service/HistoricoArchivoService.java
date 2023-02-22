package com.savia.app.service;

import com.savia.app.vo.ResponseHistoricoArchivos;
import com.savia.app.vo.ResponseMessage;
import org.springframework.http.ResponseEntity;

public interface HistoricoArchivoService {
     ResponseEntity<ResponseHistoricoArchivos> getHistoricoArchivo(int idEnfermedad,int limt, int page);
}
