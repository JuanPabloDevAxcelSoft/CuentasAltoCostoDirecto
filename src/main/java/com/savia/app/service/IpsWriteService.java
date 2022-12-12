package com.savia.app.service;

import org.springframework.http.ResponseEntity;

public interface IpsWriteService {
    ResponseEntity<String> saveIps(String nombreIps);

    ResponseEntity<String> updateIps(int idIps, String nombreIps);

    ResponseEntity<String> deleteIps(int idIps);
}
