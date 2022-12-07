package com.savia.hemofilia.service;

import com.savia.hemofilia.Dto.IpsReadDto;
import com.savia.hemofilia.model.IpsReadModel;

import java.util.List;

public interface IpsReadService {
    IpsReadDto ips(Integer id);

    List<IpsReadModel> allIps();
}
