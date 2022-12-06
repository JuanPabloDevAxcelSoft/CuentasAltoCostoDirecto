package com.savia.hemofilia.service;


import com.savia.hemofilia.Dto.EnfermedadesReadDto;
import com.savia.hemofilia.model.EnfermedadesReadModel;

import java.util.List;

public interface EnfermedadesReadService {
    EnfermedadesReadDto tblIllness(Integer id);
    List<EnfermedadesReadModel>  allIllness();

}
