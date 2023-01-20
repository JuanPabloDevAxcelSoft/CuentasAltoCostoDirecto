package com.savia.app.repository;

import java.util.List;

import com.savia.app.model.ReadCmEnfermedades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import com.savia.app.model.CmEnfermedades;

@Repository
public interface EnfermedadesReadRepository extends JpaRepository<CmEnfermedades, Integer> {

    public List<CmEnfermedades> findAllByEstado(boolean estado);
=======

@Repository
public interface EnfermedadesReadRepository extends JpaRepository<ReadCmEnfermedades, Integer> {

    public List<ReadCmEnfermedades> findAllByEstado(boolean estado);
>>>>>>> juan.dev
}
