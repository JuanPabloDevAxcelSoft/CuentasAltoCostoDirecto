package com.savia.app.util;

import com.savia.app.consultas.ConsultaLogErrores;
import com.savia.app.consultas.ConsultasPacienteCorrecto;
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
    ConsultasPacienteCorrecto consultasPacienteCorrecto;

    @Autowired
    ConsultaLogErrores consultaLogErrores;

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
                List<Object> nombColumns=consultasPacienteCorrecto.getNombreColumnasCorrecto(idEnfermedad);
                String campos="";
                for (int i = 0; i < nombColumns.size(); i++) {
                    if(i==(nombColumns.size()-1)){
                        campos+=nombColumns.get(i).toString()+" ";
                    }else{
                        campos+=nombColumns.get(i).toString()+" , ";
                    }

                }
                pacientes=consultasPacienteCorrecto.getPacienteCorrecto(new ListarPacienteDto(idEnfermedad,idIps,1048570,1,desde,hasta,"",""),false,campos);
            }else{
                //nombreColumn=consultasSql.getListAllColumTable(tablaPaso);
            }
            System.out.println(pacientes);
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
