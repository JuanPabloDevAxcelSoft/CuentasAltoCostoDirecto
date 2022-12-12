package com.savia.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savia.app.dto.IpsReadDto;
import com.savia.app.model.IpsReadModel;
import com.savia.app.repository.IpsReadRepository;
import com.savia.app.service.IpsReadService;

import java.util.List;

@Service
public class IpsReadServiceImpl implements IpsReadService {
    @Autowired
    IpsReadRepository ipsReadRepository;

    @Override
    public List<IpsReadModel> allIps() {
        return ipsReadRepository.findAll();
    }

    @Override
    public IpsReadDto ips(Integer id) {
        IpsReadModel ipsReadModel = ipsReadRepository.getById(id);
        IpsReadDto ipsReadDtoResponse = null;
        if (ipsReadModel != null) {
            ipsReadDtoResponse = new IpsReadDto(ipsReadModel.getId(), ipsReadModel.getNameIps(),
                    ipsReadModel.getFechaCreacion(),
                    ipsReadModel.getEstado());
        }
        return ipsReadDtoResponse;
    }
}
