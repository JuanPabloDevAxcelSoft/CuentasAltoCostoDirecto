package com.savia.app.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.savia.app.dto.HistoricoArchivoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.savia.app.dto.Pacientes;

@Service
public class ConvertListArrayToJson {
    private final Logger LOG = LoggerFactory.getLogger(ConvertListArrayToJson.class);
    /*
     * ListaValores: Lista de datos consultados en la base de datos
     * Columnas: Las columnas de la tabla consulta ordenados
    */
    public ArrayList<Object> setConvertListArrayToListJson(List<Object> listaValores, List<Object> columnas){
        
        ArrayList<Object> listaFinal = new ArrayList<Object>();
        try {
            for (Object valores : listaValores) {
                HashMap<String, String> map = new LinkedHashMap<>();
                if (valores.getClass().isArray()) {
                    List<Object> valoresTemp = Arrays.asList((Object[]) valores);
                    int contador = 0;
                    for (Object temp : valoresTemp) {
                        map.put(columnas.get(contador).toString(), temp.toString());
                        contador++;
                    }
                }
                listaFinal.add(map);
            }
        }catch (Exception e){
            LOG.error(e.getMessage());
        }

        return listaFinal;
    }


    /*
     * List: Lista de paciente de la tabla cm_paciente
    */
    public List<Pacientes> setConvertListObjectPaciente(List<Object> list) {
        List<Pacientes> listaFinal = new ArrayList<>();
        for (Object item : list) {
            listaFinal.add(new Pacientes(item));
        }
        return listaFinal;
    }

    public List<HistoricoArchivoDto> setConvertListObjectHistoricoArchivo(List<Object> list) {
        List<HistoricoArchivoDto> listaFinal = new ArrayList<>();
        for (Object item : list) {
            listaFinal.add(new HistoricoArchivoDto(item));
        }
        return listaFinal;
    }
}
