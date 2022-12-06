package com.savia.hemofilia.service;

import com.savia.hemofilia.valueobject.Message;
import org.springframework.http.ResponseEntity;

public interface IpsWriteService {
    ResponseEntity<Message> saveIps(String nombreIps);
    ResponseEntity<Message> updateIps(int idIps,String nombreIps);
    ResponseEntity<Message> deleteIps(int idIps);
}
