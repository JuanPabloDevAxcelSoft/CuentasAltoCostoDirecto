package com.savia.hemofilia.repository;

import com.savia.hemofilia.model.EnfermedadesReadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnfermedadesReadRepository extends JpaRepository<EnfermedadesReadModel,Integer> {
}
