package com.savia.hemofilia.repository;

import com.savia.hemofilia.model.IpsReadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpsReadRepository extends JpaRepository<IpsReadModel, Integer> {
}
