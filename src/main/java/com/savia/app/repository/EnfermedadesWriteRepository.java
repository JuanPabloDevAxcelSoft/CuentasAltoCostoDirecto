package com.savia.app.repository;

import com.savia.app.model.ModelCmEnfermedades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EnfermedadesWriteRepository extends JpaRepository<ModelCmEnfermedades, Integer> {
}
