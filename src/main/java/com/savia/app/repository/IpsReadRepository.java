package com.savia.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.savia.app.model.IpsReadModel;

@Repository
public interface IpsReadRepository extends JpaRepository<IpsReadModel, Integer> {
}
