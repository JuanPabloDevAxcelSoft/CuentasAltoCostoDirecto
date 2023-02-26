package com.savia.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import com.savia.app.util.EliminarFile;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.savia.app.constants.PathFileUpload;
import com.savia.app.util.ClassUtil;

@RestController
@RequestMapping(path = "/api/v1")
public class ExcelController {
    @Autowired
    EliminarFile eliminarFile;

    private final Logger LOG = LoggerFactory.getLogger(ExcelController.class);

    @GetMapping(value = "/exportar/excel")
    public void descargarArchivoExcel(@RequestParam("file") String file, HttpServletResponse response) {
        final String rutaArchivo = PathFileUpload.PATH_FILE_UPLOAD + "/excel/" + file;
        final String nombreArchivo = "listaRegistroSavia.xlsx";
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(rutaArchivo));
            response.setHeader("Content-Disposition","attachment; filename="+nombreArchivo+"");
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (FileNotFoundException e) {
            LOG.error("Ocurrio un error en el metodo: '" + ClassUtil.getCurrentMethodName(this.getClass()) + "' : " + e.getMessage());
        } catch (IOException e) {
            LOG.error("Ocurrio un error en el metodo: '" + ClassUtil.getCurrentMethodName(this.getClass()) + "' : " + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOG.error("Ocurrio un error en el metodo: '" + ClassUtil.getCurrentMethodName(this.getClass()) + "' al momento de cerrar : " + e.getMessage());
                }
            }
        }
    }

    @GetMapping(value = "/eliminar/excel")
    public  void setRemoveFile(@RequestParam("clave") String clave) {
        try {
            eliminarFile.setRemoveFile(clave);
        }catch (Exception e){
            LOG.error(e.getMessage());
        }
    }
}
