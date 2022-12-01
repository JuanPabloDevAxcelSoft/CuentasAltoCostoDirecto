package com.savia.hemofilia.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import com.savia.hemofilia.model.IllnesModel;
import com.savia.hemofilia.repository.IllnesRepository;
import com.savia.hemofilia.service.EnfermedadesServiceDirect;
import com.savia.hemofilia.service.UploadService;
import com.savia.hemofilia.valueobject.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl implements UploadService {
	private String folder="C:/Users/JuanSuarez/Desktop/Savia/hemofilia/Spring/EnfermedadaesPrueba/cargas/";
	private String folder2="C:/Users/JuanSuarez/Desktop/Savia/cargas/";
	@Autowired
	EnfermedadesServiceDirect enfermedadesServiceDirect;
	@Autowired
	IllnesRepository illnesRepository;

	@Override
	public ResponseEntity<Message> save(MultipartFile file, int idEnfermedad, String ipsEmisora) {
		if (!file.isEmpty()) {
			try {
				IllnesModel illnesModel = enfermedadesServiceDirect.tblIllness(idEnfermedad);
				byte [] bytes= file.getBytes();
				Path path = Paths.get( folder+idEnfermedad+ipsEmisora+".csv" );
				Files.write(path, bytes);
				enfermedadesServiceDirect.loadDataBase(String.valueOf(path),illnesModel.getNameTables());
				return ResponseEntity.ok()
						.body(new Message("El archivo se cargo correctamente"));
			} catch (IOException e) {
				return ResponseEntity.badRequest()
						.body(new Message("El archivo: tiene un error  : "+e.getLocalizedMessage()));
			}
		}else{
			return ResponseEntity.badRequest()
					.body(new Message("No se envio ningun archivo"));
		}
	}



}
