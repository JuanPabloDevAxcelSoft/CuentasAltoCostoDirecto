package com.savia.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.savia.app.model.CmEnfermedades;

@Repository
public interface EnfermedadesWriteRepository extends JpaRepository<CmEnfermedades, Integer> {
}
