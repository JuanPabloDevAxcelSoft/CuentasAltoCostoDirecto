package com.savia.app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.savia.app.valueobject.Message;

public interface UploadService {
    ResponseEntity<Message> save(MultipartFile file, int idEnfermedad, int ipsEmisora);
}
