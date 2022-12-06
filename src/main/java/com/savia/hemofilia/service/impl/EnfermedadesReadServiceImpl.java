package com.savia.hemofilia.service.impl;


import com.savia.hemofilia.Dto.EnfermedadesReadDto;
import com.savia.hemofilia.model.EnfermedadesReadModel;
import com.savia.hemofilia.repository.EnfermedadesReadRepository;
import com.savia.hemofilia.repository.EnfermedadesWriteRepository;
import com.savia.hemofilia.service.EnfermedadesReadService;
import com.savia.hemofilia.valueobject.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class EnfermedadesReadServiceImpl implements EnfermedadesReadService {
    @Autowired
    EnfermedadesReadRepository enfermedadesRepository;
    @Override
    public List<EnfermedadesReadModel> allIllness() {
        return enfermedadesRepository.findAll();
    }
    @Override
    public EnfermedadesReadDto tblIllness(Integer id) {
       EnfermedadesReadModel enfermedadesReadModel= enfermedadesRepository.getById(id);
        return new EnfermedadesReadDto(enfermedadesReadModel.getId(),enfermedadesReadModel.getNameTables(),
                enfermedadesReadModel.getFechaCreacion(),enfermedadesReadModel.getEstado());
    }

}
