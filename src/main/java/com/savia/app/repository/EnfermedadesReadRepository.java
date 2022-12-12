package com.savia.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.savia.app.model.EnfermedadesReadModel;

@Repository
public interface EnfermedadesReadRepository extends JpaRepository<EnfermedadesReadModel, Integer> {

    public List<EnfermedadesReadModel> findAllByEstado(boolean estado);
}
