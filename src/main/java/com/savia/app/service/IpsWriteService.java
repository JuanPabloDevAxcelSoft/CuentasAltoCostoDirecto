package com.savia.app.service;

import org.springframework.http.ResponseEntity;

import com.savia.app.vo.ResponseMessage;

public interface IpsWriteService {
    ResponseEntity<ResponseMessage> saveIps(String nombreIps);

    ResponseEntity<ResponseMessage> updateIps(int idIps, String nombreIps);

    ResponseEntity<ResponseMessage> deleteIps(int idIps);
}
