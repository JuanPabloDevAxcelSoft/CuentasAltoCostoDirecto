package com.savia.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.savia.app.service.ConsultasTableService;

public class ConsultaTable<T> implements ConsultasTableService<T> {

    @Autowired
    private ConsultasTableService<T> consultasTableService;

    @Override
    public Page<T> findAllPaginated(int size, int pageSize) {
        Page<T> list = consultasTableService.findAllPaginated(size, pageSize);
        return list;
    }

}
