package com.savia.app.util;

import com.savia.app.constants.KeySsEmitter;
import com.savia.app.constants.PathFileUpload;
import com.savia.app.consultas.ConsultaLogErrores;
import com.savia.app.consultas.ConsultasPacienteCorrecto;
import com.savia.app.dto.ListarPacienteDto;
import com.savia.app.dto.PacienteExcelDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class GenerarExcelApartirObjecto {
    private final Logger LOG = LoggerFactory.getLogger(ObtenerColumnasTabla.class);

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ConsultasPacienteCorrecto consultasPacienteCorrecto;

    @Autowired
    ConsultaLogErrores consultaLogErrores;

    public void getProcesoArchivoExcel(PacienteExcelDto pacienteExcelDto, SseEmitter sseEmitter) {
        try {
            List<Object> pacientes = new ArrayList<Object>();

            String desde = pacienteExcelDto.getDesde();
            String hasta = pacienteExcelDto.getHasta();
            int idEnfermedad = pacienteExcelDto.getIdEnfermedad();
            int idIps = pacienteExcelDto.getIdIps();
            List<Object> nombreColumn = new ArrayList<Object>();

            if (pacienteExcelDto.isBandera()) {
                nombreColumn = consultasPacienteCorrecto.getListAllColumTable(idEnfermedad);
                String campos = "";
                for (int i = 0; i < nombreColumn.size(); i++) {
                    campos += nombreColumn.get(i).toString() + " AS " + nombreColumn.get(i).toString().replace(".", "_")
                            + " ";
                    if (!(i == (nombreColumn.size() - 1))) {
                        campos += " , ";
                    }
                }
                pacientes = consultasPacienteCorrecto.getPacienteCorrecto(
                        new ListarPacienteDto(idEnfermedad, idIps, 1048570, 1, desde, hasta, "", ""), false, campos,false);
            } else {
                nombreColumn = consultaLogErrores.getListAllColumTable(idEnfermedad);
                pacientes = consultaLogErrores.getPacienteError(idEnfermedad, 1048570, 1, desde, hasta);
            }
            this.setGeneracionArchivoExcel(nombreColumn, pacientes, sseEmitter);

        } catch (Exception e) {
            LOG.error("Ocurrio un error en el metodo: '" + ClassUtil.getCurrentMethodName(this.getClass()) + "'" + e.getMessage());
        }
    }

    public void setGeneracionArchivoExcel(List<Object> namesHeader, List<Object> data, SseEmitter sseEmitter) {
        try {
            // Generar archivo de excel
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Pacientes");
            // encabezado
            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Row row = sheet.createRow(0);
            for (int i = 0; i < namesHeader.size(); i++) {
                Cell cell = row.createCell(i);
                cell.setCellStyle(style);
                cell.setCellValue("V" + i + namesHeader.get(i).toString());
            }

            // data
            int contadorRow = 1;
            for (Object valores : data) {
                int porcentArchivo = contadorRow * 100 / data.size();
                if (valores.getClass().isArray()) {
                    List<Object> valoresTemporales = Arrays.asList((Object[]) valores);
                    Row rowBody = sheet.createRow(contadorRow);
                    int contadorColumn = 0;
                    for (Object temp : valoresTemporales) {
                        Cell cellBody = rowBody.createCell(contadorColumn);
                        cellBody.setCellValue((temp == null) ? "" : temp.toString());
                        contadorColumn++;
                    }
                    enviarNotificacion(Integer.toString(porcentArchivo), sseEmitter);
                    contadorRow++;
                }
            }
            Date dateNow = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-kk-mm-ss");
            String predicado = "reporte-" + simpleDateFormat.format(dateNow) + ".xlsx";
            final String nombreArchivo = PathFileUpload.PATH_FILE_UPLOAD + "excel\\" + predicado;
            File archivoCrear = new File(nombreArchivo);
            archivoCrear.setReadable(true, false);
            archivoCrear.setWritable(true, false);
            archivoCrear.setExecutable(true, false);
            FileOutputStream fileOut = new FileOutputStream(archivoCrear);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            enviarNotificacion(predicado, sseEmitter);

        } catch (FileNotFoundException e) {
            LOG.error("Ocurrio un error en el metodo: '" + ClassUtil.getCurrentMethodName(this.getClass()) + "'" + e.getMessage());
        } catch (IOException e) {
            LOG.error("Ocurrio un error en el metodo: '" + ClassUtil.getCurrentMethodName(this.getClass()) + "'" + e.getMessage());
        }
    }

    public void enviarNotificacion(String porcentajeCarga, SseEmitter sseEmitter) {
        String messageLogger;
        try {
            boolean validacionNumero = isNumber(porcentajeCarga);
            boolean bandera = ((validacionNumero == true) ? false : true);
            String json = "{'bandera':" + bandera + ", 'valor':" + porcentajeCarga + "}";
            sseEmitter.send(SseEmitter.event().name(KeySsEmitter.KEY_PROCESS_GERERAR.toString()).data(json));
            messageLogger = json;
            if (bandera && porcentajeCarga.endsWith(".xlsx")){
                sseEmitter.complete();
            }
        } catch (Exception e) {
            messageLogger = "El emitter fue removido de la lista de subcritos";
        }
        this.LOG.info(messageLogger);
    }

    private boolean isNumber(String porcentajeCarga) {
        String messageLogger;
        try {
            Integer.parseInt(porcentajeCarga);
            return true;
        } catch (Exception e) {
            if (!porcentajeCarga.endsWith(".xlsx")) {
                return true;
            }
            messageLogger = "Ocurrio un error en la conversion " + e.getMessage();
        }
        this.LOG.info(messageLogger);
        return false;
    }
}
