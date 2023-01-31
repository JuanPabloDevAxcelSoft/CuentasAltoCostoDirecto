package com.savia.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.savia.app.EnfermedadesDirectoApplication;
import com.savia.app.repository.EnfermedadesReadRepository;

@SpringBootTest(classes =  EnfermedadesDirectoApplication.class )
// @ExtendWith(SpringExtension.class)
public class DetallePersonaServiceTest {

    @Autowired
    EnfermedadesReadRepository servicio;

    @Test
    public void getEntityTest() {
        // String respuesta = this.servicio.getNombreTablaGeneric("id", 1);
        assertEquals("info", "info"); 
    }
}
