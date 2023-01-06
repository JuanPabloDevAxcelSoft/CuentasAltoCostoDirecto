package com.savia.app.repository;

// import com.savia.app.model.DetallePaciente;


// import java.util.List;

import com.savia.app.model.CmPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePacienteRepository extends JpaRepository<CmPaciente, Integer> {

    // @Query("SELECT u.* FROM tbl_detalle_paciente u WHERE u.id_paciente = :id")
    // List<DetallePaciente> findByIdPaciente(@Param("id") Long id);

}