package com.savia.app.repository;

import java.util.List;

import com.savia.app.model.ModelCmEnfermedades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EnfermedadesReadRepository extends JpaRepository<ModelCmEnfermedades, Integer> {

    public List<ModelCmEnfermedades> findAllByEstado(boolean estado);
}
