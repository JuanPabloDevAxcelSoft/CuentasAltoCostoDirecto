package com.savia.hemofilia.repository;

import com.savia.hemofilia.model.IpsWriteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpsWriteRepository extends JpaRepository<IpsWriteModel,Integer> {
}
