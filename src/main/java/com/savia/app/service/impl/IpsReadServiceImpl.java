package com.savia.app.service.impl;

import com.savia.app.model.ReadCmIps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savia.app.dto.IpsReadDto;
import com.savia.app.repository.ReadIpsRepository;
import com.savia.app.service.IpsReadService;

import java.util.List;

@Service
public class IpsReadServiceImpl implements IpsReadService {
    @Autowired
    ReadIpsRepository readIpsRepository;

    @Override
    public List<ReadCmIps> allIps() {
        return readIpsRepository.findAll();
    }

    @Override
    public IpsReadDto ips(Integer id) {
        ReadCmIps ipsReadModel = readIpsRepository.getById(id);
        IpsReadDto ipsReadDtoResponse = null;
        if (ipsReadModel != null) {
            ipsReadDtoResponse = new IpsReadDto(ipsReadModel.getId(), ipsReadModel.getNameIps(),
                    ipsReadModel.getFechaCreacion(),
                    ipsReadModel.getEstado());
        }
        return ipsReadDtoResponse;
    }
}
