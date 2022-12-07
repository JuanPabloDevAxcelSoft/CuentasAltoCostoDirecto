package com.savia.hemofilia.repository;

import com.savia.hemofilia.model.EnfermedadesWriteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnfermedadesWriteRepository extends JpaRepository<EnfermedadesWriteModel, Integer> {
}
