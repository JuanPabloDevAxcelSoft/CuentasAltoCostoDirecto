package com.savia.app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.savia.app.vo.ResponseMessage;

public interface UploadService {
    ResponseEntity<ResponseMessage> save(MultipartFile file, int idEnfermedad, int ipsEmisora);
}
