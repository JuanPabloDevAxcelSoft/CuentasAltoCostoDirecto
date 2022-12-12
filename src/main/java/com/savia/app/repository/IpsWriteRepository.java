package com.savia.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.savia.app.model.IpsWriteModel;

@Repository
public interface IpsWriteRepository extends JpaRepository<IpsWriteModel, Integer> {
}
