package com.savia.app.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.savia.app.constants.PathFileUpload;
import com.savia.app.dto.EnfermedadesReadDto;
import com.savia.app.service.CargaDirectaService;
import com.savia.app.service.EnfermedadesReadService;
import com.savia.app.service.UploadService;
import com.savia.app.valueobject.Message;

@Service
public class UploadServiceImpl implements UploadService {

	private String folder = PathFileUpload.PATH_FILE_UPLOAD;

	@Autowired
	EnfermedadesReadService enfermedadesServiceDirect;

	@Autowired
	CargaDirectaService cargaDirectaService;

	@Override
	public ResponseEntity<Message> save(MultipartFile file, int idEnfermedad, int ipsEmisora) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-kk-mm-ss");
		if (!file.isEmpty()) {
			try {
				EnfermedadesReadDto illnesModel = enfermedadesServiceDirect.findIllnessById(idEnfermedad);
				byte[] bytes = file.getBytes();
				Path path = Paths.get(folder + idEnfermedad + ipsEmisora + format.format(date) + ".csv");
				Files.write(path, bytes);
				cargaDirectaService.loadDataBase(String.valueOf(path), illnesModel.getNameTables());
				return ResponseEntity.ok()
						.body(new Message("El archivo se cargo correctamente"));
			} catch (IOException e) {
				return ResponseEntity.badRequest()
						.body(new Message("El archivo: tiene un error : " +
								e.getLocalizedMessage()));
			}
		} else {
			return ResponseEntity.badRequest()
					.body(new Message("No se envio ningun archivo"));
		}
	}
}
