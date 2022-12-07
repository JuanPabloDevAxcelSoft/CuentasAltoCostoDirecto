package com.savia.hemofilia.service;

<<<<<<< HEAD
import com.savia.hemofilia.dto.IpsReadDto;
=======
<<<<<<< HEAD

import com.savia.hemofilia.dto.IpsReadDto;
=======
import com.savia.hemofilia.Dto.IpsReadDto;
>>>>>>> 18f0df31e80e18a202f11965cb73112d3b170592
>>>>>>> 9fc4a6c82e455563e073a854ab90c0019b34ceff
import com.savia.hemofilia.model.IpsReadModel;

import java.util.List;

public interface IpsReadService {
    IpsReadDto ips(Integer id);

    List<IpsReadModel> allIps();
}
