package com.savia.app.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.savia.app.constants.PathFileUpload;
import com.savia.app.service.CargaDirectaService;
import com.savia.app.service.EnfermedadesReadService;
import com.savia.app.service.UploadService;
import com.savia.app.vo.ResponseMessage;

@Service
public class UploadServiceImpl implements UploadService {

	private String folder = PathFileUpload.PATH_FILE_UPLOAD;

	@Autowired
	EnfermedadesReadService enfermedadesServiceDirect;

	@Autowired
	CargaDirectaService cargaDirectaService;

	@Override
	public ResponseEntity<ResponseMessage> save(MultipartFile file, int idEnfermedad, int ipsEmisora) {
		ResponseMessage response = new ResponseMessage();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-kk-mm-ss");

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				Path path = Paths.get(folder + idEnfermedad + ipsEmisora+"-" + format.format(date) + ".csv");
				Files.write(path, bytes);
				String pathFile = String.valueOf(path).replace("\\", "/");
				cargaDirectaService.loadDataBaseDirect(pathFile, idEnfermedad);
				response.setMessage("El archivo fue cargado correctamente.");
				response.setStatus(HttpStatus.OK);

			} catch (IOException e) {
				response.setMessage("El archivo presenta algun error, No se puede ser procesado.");
			}
		} else {
			response.setMessage("No fue enviado ningun archivo, Por tanto no se puede completar la acción.");
		}
		return ResponseEntity.ok().body(response);
	}
}
