package com.savia.app.util;

import com.savia.app.dto.PacienteExcelDto;
import com.savia.app.service.EnfermedadesReadService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenerarExcelApartirObjecto {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ConsultasSql consultasSql;

    @Autowired
    EnfermedadesReadService enfermedadesReadService;

    public boolean isExcel(PacienteExcelDto pacienteExcelDto) {
        List<Object> pacientes= new ArrayList<Object>();
        String desde= pacienteExcelDto.getDesde();
        String hasta=pacienteExcelDto.getHasta();
        int idEnfermedad=pacienteExcelDto.getIdEnfermedad();
        String tablaPaso = enfermedadesReadService.getNombreTablaGeneric("nombre_tabla_paso", idEnfermedad);
        List<Object> nombreColumn=consultasSql.getListAllColumTable(tablaPaso);
        if(pacienteExcelDto.isBandera()){
        }else{
            pacientes= consultasSql.getPacienteError(tablaPaso,1048500,1,desde,hasta);
        }
        //Generar archivo de excel
        Workbook workbook= new XSSFWorkbook();
        Sheet sheet= workbook.createSheet("Pacientes");
        //encabezado
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Row row= sheet.createRow(0);
        for (int i = 0; i < nombreColumn.size(); i++) {
            Cell  cell=row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue("V"+i+nombreColumn.get(i).toString());
        }
        //data
        


        try {
            FileOutputStream fileOut = new FileOutputStream(new File("C:\\Users\\JuanSuarez\\Desktop\\datos.xlsx"));
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}

