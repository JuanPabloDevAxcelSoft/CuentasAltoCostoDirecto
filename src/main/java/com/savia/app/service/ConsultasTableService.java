package com.savia.app.service;

import org.springframework.data.domain.Page;

public interface ConsultasTableService<T> {
    public Page<T> findAllPaginated(int size, int pageSize);
}
