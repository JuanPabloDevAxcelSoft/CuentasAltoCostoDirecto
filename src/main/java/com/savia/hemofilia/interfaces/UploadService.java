package com.savia.hemofilia.interfaces;

import com.savia.hemofilia.valueobject.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    ResponseEntity<Message> save(MultipartFile file, int idEnfermedad, String ipsEmisora);
}
