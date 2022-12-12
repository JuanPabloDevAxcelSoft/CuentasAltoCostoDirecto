package com.savia.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultasRepository<T> extends JpaRepository<T, Integer> {

}
