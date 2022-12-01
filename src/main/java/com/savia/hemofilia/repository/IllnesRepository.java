package com.savia.hemofilia.repository;

import com.savia.hemofilia.model.IllnesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IllnesRepository extends JpaRepository<IllnesModel,Integer>  {
}
