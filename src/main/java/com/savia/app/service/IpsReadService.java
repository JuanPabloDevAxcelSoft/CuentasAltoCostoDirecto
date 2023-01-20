package com.savia.app.service;

import java.util.List;

import com.savia.app.dto.IpsReadDto;
import com.savia.app.model.ReadCmIps;

public interface IpsReadService {
    IpsReadDto ips(Integer id);

    List<ReadCmIps> allIps();
}
