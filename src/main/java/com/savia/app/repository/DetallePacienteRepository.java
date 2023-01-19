package com.savia.app.repository;

// import com.savia.app.model.DetallePaciente;


// import java.util.List;

import com.savia.app.model.CmPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePacienteRepository extends JpaRepository<CmPaciente, Integer> {


}