package com.savia.app.util;

import com.savia.app.dto.ListarPacienteDto;
import com.savia.app.dto.PacienteExcelDto;
import com.savia.app.service.EnfermedadesReadService;
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
import java.util.Arrays;
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
        try{
            List<Object> pacientes= new ArrayList<Object>();
            String desde= pacienteExcelDto.getDesde();
            String hasta=pacienteExcelDto.getHasta();
            int idEnfermedad=pacienteExcelDto.getIdEnfermedad();
            int idIps=pacienteExcelDto.getIdIps();
            List<Object> nombreColumn= new ArrayList<Object>();
            if(pacienteExcelDto.isBandera()){
                String tablaFin = enfermedadesReadService.getNombreTablaGeneric("nom_tab_fin", idEnfermedad);
                //pacientes=consultasSql.getPacienteCorrecto(new ListarPacienteDto(idEnfermedad,idIps,1048500,1,desde,hasta,"",""),false);
                List<Object[]> pruebaObject=consultasSql.consultaPrueba();
                nombreColumn.addAll(consultasSql.getListAllColumTable("cm_paciente"));
                nombreColumn.addAll(consultasSql.getListAllColumTable("cm_detalle_paciente"));
                nombreColumn.addAll(consultasSql.getListAllColumTable(tablaFin));
            }else{
                String tablaPaso = enfermedadesReadService.getNombreTablaGeneric("nombre_tabla_paso", idEnfermedad);
                pacientes= consultasSql.getPacienteError(tablaPaso,1048500,1,desde,hasta);
                nombreColumn=consultasSql.getListAllColumTable(tablaPaso);
            }
            //generacionEcxel(nombreColumn,pacientes);
            return true;
        }  catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public void generacionEcxel(List<Object> namesHeader,List<Object> data){
        //Generar archivo de excel
        Workbook workbook= new XSSFWorkbook();
        Sheet sheet= workbook.createSheet("Pacientes");
        //encabezado
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Row row= sheet.createRow(0);
        for (int i = 0; i < namesHeader.size(); i++) {
            Cell  cell=row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue("V"+i+namesHeader.get(i).toString());
        }
        //data
        int contadorRow=1;
        for (Object valores:data) {
            if (valores.getClass().isArray()) {
                List<Object> valoresTemporales= Arrays.asList((Object[]) valores);
                Row rowBody=sheet.createRow(contadorRow);
                int contadorColumn=0;
                for (Object temp:valoresTemporales) {
                    Cell cellBody=rowBody.createCell(contadorColumn);
                    if (temp==null){
                        cellBody.setCellValue("");
                    }else{
                        cellBody.setCellValue(temp.toString());
                    }
                    contadorColumn++;
                }
                contadorRow++;
            }
        }
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
    }
}
