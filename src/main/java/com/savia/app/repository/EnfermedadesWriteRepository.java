package com.savia.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.savia.app.model.EnfermedadesWriteModel;

@Repository
public interface EnfermedadesWriteRepository extends JpaRepository<EnfermedadesWriteModel, Integer> {
}
