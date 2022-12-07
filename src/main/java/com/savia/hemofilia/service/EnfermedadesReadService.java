package com.savia.hemofilia.service;

<<<<<<< HEAD

import com.savia.hemofilia.dto.EnfermedadesReadDto;
=======
import com.savia.hemofilia.Dto.EnfermedadesReadDto;
>>>>>>> 18f0df31e80e18a202f11965cb73112d3b170592
import com.savia.hemofilia.model.EnfermedadesReadModel;

import java.util.List;

public interface EnfermedadesReadService {
    EnfermedadesReadDto tblIllness(Integer id);

    List<EnfermedadesReadModel> allIllness();

}
