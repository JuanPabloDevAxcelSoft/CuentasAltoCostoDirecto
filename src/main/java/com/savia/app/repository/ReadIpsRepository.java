package com.savia.app.repository;

import com.savia.app.model.ReadCmIps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ReadIpsRepository extends JpaRepository<ReadCmIps, Integer> {
}
