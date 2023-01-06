package com.savia.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.savia.app.model.CmEnfermedades;

@Repository
public interface EnfermedadesReadRepository extends JpaRepository<CmEnfermedades, Integer> {

    public List<CmEnfermedades> findAllByEstado(boolean estado);
}
