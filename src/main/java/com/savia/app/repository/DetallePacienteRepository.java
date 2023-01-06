package com.savia.app.repository;

// import com.savia.app.model.DetallePaciente;
import com.savia.app.model.Paciente;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePacienteRepository extends JpaRepository<Paciente, Integer> {

    // @Query("SELECT u.* FROM tbl_detalle_paciente u WHERE u.id_paciente = :id")
    // List<DetallePaciente> findByIdPaciente(@Param("id") Long id);

}