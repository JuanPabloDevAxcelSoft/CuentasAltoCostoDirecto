package com.savia.app.services;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.savia.app.repository.EnfermedadesReadRepository;
import com.savia.app.service.impl.EnfermedadesReadServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EnfermedadesReadServiceImplTest {
    
    @Mock
    private EntityManager em;

    @Mock
    private EnfermedadesReadRepository repository;

    @InjectMocks
    private EnfermedadesReadServiceImpl services;

    private ReadCmEnfermedades response;

    @InjectMocks
    EnfermedadesReadServiceImpl impl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        this.response = new ReadCmEnfermedades();
        this.response.setEstado(true);
        this.response.setFechaCreacion(new Date());
        this.response.setId(1);
        this.response.setNomTabFin("cm_hem dasdasasdasdofilia_paso");
        this.response.setNombre("Enfermedad");
        this.response.setNombreClaseValidacion("com.app.validacion");
        this.response.setNombreTabla("cm_hemofilia_final");
    
        reset(em);
    
    }

    @Test
    void getAllEnfermedadesTest () {
        when(this.repository.findAllByEstado(true)).thenReturn(Arrays.asList(this.response));
        Object item = this.services.getAllEnfermedades().getBody().getItem();
        List<Object> list = this.services.getAllEnfermedades().getBody().getData();    
        assertNull(item);
        assertNotNull(list);    
        assertNotEquals(0, list.size());
    }

    @Test
    void getEnfermedadByIdTest() {
        Integer id = 1;
        when(this.repository.getById(1)).thenReturn(this.response);
        Object objetoEnfermedad = this.services.tblIllness(id).getBody().getItem();
        assertNotNull(objetoEnfermedad);
    }


    // @Test
    // void getNombreTablaGenericTest() {
    //     final String columna = "nombre";
    //     final Integer id = 3;

    //     String pureSql = "SELECT "+columna+" FROM cm_enfermedades AS enf ";
    //     pureSql += "WHERE enf.id = :id AND enf.estado = :estado ;";
        
    //     Query query = mock(Query.class);
    //     query.setParameter("id", 1);
    //     query.setParameter("estado", 1);

    //     when(this.em.createNativeQuery(pureSql)).thenReturn(query);
        
    //     EnfermedadesReadServiceImpl servicio = new EnfermedadesReadServiceImpl();
    //     servicio.setEntityManager(this.entityManager.getEntityManager());
    //     String informacion = servicio.getNombreTablaGeneric(columna, id);
        
    //     assertNotNull(informacion);
    //     assertEquals("renales", informacion);
    // }
}
