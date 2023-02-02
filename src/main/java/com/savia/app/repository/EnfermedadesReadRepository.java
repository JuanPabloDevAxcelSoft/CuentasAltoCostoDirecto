package com.savia.app.repository;

import java.util.List;

import com.savia.app.model.ReadCmEnfermedades;
import com.savia.app.service.EnfermedadesReadService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EnfermedadesReadRepository extends JpaRepository<ReadCmEnfermedades, Integer>, EnfermedadesReadService {

    public List<ReadCmEnfermedades> findAllByEstado(boolean estado);
}
