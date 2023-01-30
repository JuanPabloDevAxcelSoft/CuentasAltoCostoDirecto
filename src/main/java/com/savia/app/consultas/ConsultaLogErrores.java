package com.savia.app.consultas;

import com.savia.app.constants.EnumNombreColumnasTablaCmEnfermedad;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaLogErrores extends ConsultasAbstract {
    public List<Object> getPacienteError(int idEnfermedad, int limit, int page, String desde, String hasta) {
        final String nombreTablaEnfermedadPaso=nombreTablaEnfermedad(idEnfermedad, EnumNombreColumnasTablaCmEnfermedad.nombre_tabla_paso.toString());
        List<Object> listPacienteError = new ArrayList<Object>();
        try {
            String pureSql = "SELECT cep.* ";
            pureSql += " FROM " + nombreTablaEnfermedadPaso + " as cep ";
            pureSql += " WHERE cep.campo_leido=1 AND ";
            pureSql += "DATE(concat(SUBSTRING_INDEX(SUBSTRING_INDEX(cep.clave_archivo, '-', 2), '-', -1),'-',";
            pureSql += "SUBSTRING_INDEX(SUBSTRING_INDEX(cep.clave_archivo, '-', 3), '-', -1),'-',";
            pureSql += "SUBSTRING_INDEX(SUBSTRING_INDEX(cep.clave_archivo, '-', 4), '-', -1)))";
            pureSql += " BETWEEN '" + desde + "' AND '" + hasta + "' ";
            pureSql += " ORDER BY cep.id ";
            pureSql += " LIMIT " + ((page - 1) * limit) + ", " + limit + ";";
            query = entityManager.createNativeQuery(pureSql);
            listPacienteError = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPacienteError;
    }


}
