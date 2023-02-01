package com.savia.app.util;

import com.savia.app.constants.PathFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EliminarFile {
    private final Logger LOG = LoggerFactory.getLogger(ObtenerColumnasTabla.class);
    public void setRemoveFile(String clave){
        final  String ruta=PathFileUpload.PATH_FILE_UPLOAD +"\\upload\\"+clave;
        try {
            File archivo= new File(ruta);
            LOG.info((archivo.delete())? "El archivo fue eliminado con exito":"No se puede eliminar el archivo");
        }catch (Exception e){
            LOG.error(e.getMessage());
        }
    }
}
