package com.savia.hemofilia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savia.hemofilia.interfaces.EnfermedadesServiceDirect;
import com.savia.hemofilia.model.IllnesModel;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EnfermedadesController {

    @Autowired
    private EnfermedadesServiceDirect enfermedadesServiceDirect;

    @GetMapping("/enfermedades")
    public List<IllnesModel> getAllIllness() {
        try {
            return enfermedadesServiceDirect.allIllness();
        } catch (Exception e) {
            return null;
        }
    }

    /* Por desarrollar */
    @GetMapping("/ips")
    public List<Object> getAllIps() {
        try {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
