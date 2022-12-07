package com.savia.hemofilia.service.impl;

import com.savia.hemofilia.Dto.IpsReadDto;
import com.savia.hemofilia.model.IpsReadModel;
import com.savia.hemofilia.repository.IpsReadRepository;
import com.savia.hemofilia.service.IpsReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
