package com.savia.app.service;

import java.util.List;

import com.savia.app.dto.IpsReadDto;
import com.savia.app.model.IpsReadModel;

public interface IpsReadService {
    IpsReadDto ips(Integer id);

    List<IpsReadModel> allIps();
}
