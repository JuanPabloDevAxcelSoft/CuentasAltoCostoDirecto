package com.savia.app.repository;

import com.savia.app.model.CmDetallePaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CmDetallePacienteRepository extends JpaRepository<CmDetallePaciente,Long> {
}
