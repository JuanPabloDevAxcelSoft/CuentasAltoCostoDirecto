package com.savia.hemofilia.service;

<<<<<<< HEAD
import com.savia.hemofilia.dto.EnfermedadesReadDto;
=======
<<<<<<< HEAD

import com.savia.hemofilia.dto.EnfermedadesReadDto;
=======
import com.savia.hemofilia.Dto.EnfermedadesReadDto;
>>>>>>> 18f0df31e80e18a202f11965cb73112d3b170592
>>>>>>> 9fc4a6c82e455563e073a854ab90c0019b34ceff
import com.savia.hemofilia.model.EnfermedadesReadModel;

import java.util.List;

public interface EnfermedadesReadService {
    EnfermedadesReadDto tblIllness(Integer id);

    List<EnfermedadesReadModel> allIllness();

}
