package com.savia.hemofilia.service.impl;

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
import com.savia.hemofilia.repository.EnfermedadesReadRepository;
import com.savia.hemofilia.service.EnfermedadesReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        EnfermedadesReadModel enfermedadesReadModel = enfermedadesRepository.getById(id);
        EnfermedadesReadDto enferReadDtoResponse = null;
        if (enfermedadesReadModel != null) {
            enferReadDtoResponse = new EnfermedadesReadDto(enfermedadesReadModel.getId(),
                    enfermedadesReadModel.getNameTables(),
                    enfermedadesReadModel.getFechaCreacion(), enfermedadesReadModel.getEstado());
        }
        return enferReadDtoResponse;
    }

}
