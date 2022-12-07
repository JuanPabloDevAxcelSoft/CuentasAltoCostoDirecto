package com.savia.hemofilia.service;


import com.savia.hemofilia.dto.IpsReadDto;
import com.savia.hemofilia.model.IpsReadModel;

import java.util.List;

public interface IpsReadService {
    IpsReadDto ips(Integer id);
    List<IpsReadModel> allIps();
}
