package com.savia.app.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import com.savia.app.dto.Pacientes;

@Service("convertList")
public class ConvertListArrayToJson {
    /*
     * ListaValores: Lista de datos consultados en la base de datos
     * Columnas: Las columnas de la tabla consulta ordenados
    */
    public ArrayList<Object> setConvertListArrayToListJson(List<Object> listaValores, List<Object> columnas)
            throws JSONException {

        ArrayList<Object> listaFinal = new ArrayList<Object>();
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
}
