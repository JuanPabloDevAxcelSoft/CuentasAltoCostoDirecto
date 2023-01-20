package com.savia.app.repository;

import com.savia.app.model.WriteCmIps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface IpsWriteRepository extends JpaRepository<WriteCmIps, Integer> {
}
