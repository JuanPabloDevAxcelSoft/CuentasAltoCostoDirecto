package com.savia.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.savia.app.dto.IpsReadDto;
import com.savia.app.model.IpsReadModel;
import com.savia.app.service.IpsReadService;
import com.savia.app.service.IpsWriteService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class IpsController {

    @Autowired
    private IpsReadService ipsReadService;

    @Autowired
    private IpsWriteService ipsWriteService;

    @GetMapping("/ips")
    public List<IpsReadModel> getAllIps() {
        try {
            return ipsReadService.allIps();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/ips/{idIps}")
    public IpsReadDto getIps(@PathVariable("idIps") int idIps) {
        try {
            return ipsReadService.ips(idIps);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PutMapping("/ips")
    public ResponseEntity<String> updateIps(@RequestParam("idIps") int idIps,
            @RequestParam("enfermedad") String nombreIps) {
        return ipsWriteService.updateIps(idIps, nombreIps);
    }

    @PostMapping("/ips")
    public ResponseEntity<String> saveIps(@RequestParam("ips") String nombreIps) {
        return ipsWriteService.saveIps(nombreIps);
    }

    @DeleteMapping("/ips/{idIps}")
    public ResponseEntity<String> updateIps(@PathVariable("idIps") int idIps) {
        return ipsWriteService.deleteIps(idIps);
    }
}
