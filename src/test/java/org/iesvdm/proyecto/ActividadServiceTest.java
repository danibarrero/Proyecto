package org.iesvdm.proyecto;

import org.iesvdm.proyecto.domain.Actividad;
import org.iesvdm.proyecto.repository.ActividadRepository;
import org.iesvdm.proyecto.service.ActividadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ActividadServiceTest {

    @Autowired
    private ActividadService actividadService;

    @Autowired
    private ActividadRepository actividadRepository;

    @BeforeEach
    public void clean() {
        actividadRepository.deleteAll();
    }

    // Test para listar todas las actividades
    @Test
    public void testGetAllActividades() {
        Actividad actividad1 = new Actividad();
        actividad1.setNombre("Excursión");
        actividad1.setDescripcion("Caminata por la montaña");
        actividad1.setPais("España");
        actividad1.setCiudad("Madrid");
        actividad1.setPrecio(10.0);
        actividad1.setFecha(LocalDate.parse("2026-05-11"));

        Actividad actividad2 = new Actividad();
        actividad2.setNombre("Concierto");
        actividad2.setDescripcion("Concierto en vivo de rock");
        actividad2.setPais("España");
        actividad2.setCiudad("Barcelona");
        actividad2.setPrecio(20.0);
        actividad2.setFecha(LocalDate.parse("2026-06-15"));

        actividadRepository.save(actividad1);
        actividadRepository.save(actividad2);

        List<Actividad> actividades = actividadService.getAllActividades();

        assertNotNull(actividades, "La lista de actividades no debe ser nula.");
        assertEquals(2, actividades.size(), "El número de actividades debe ser 2.");
        assertTrue(actividades.stream().anyMatch(a -> a.getNombre().equals("Excursión")));
        assertTrue(actividades.stream().anyMatch(a -> a.getNombre().equals("Concierto")));
    }

    // Test para crear una actividad y verificar que se guarda
    @Test
    public void testCreateActividad() {
        Actividad actividad = new Actividad();
        actividad.setNombre("Excursión");
        actividad.setDescripcion("Caminata por la montaña");
        actividad.setPais("España");
        actividad.setCiudad("Madrid");
        actividad.setPrecio(10.0);
        actividad.setFecha(LocalDate.parse("2026-05-11"));

        Actividad savedActividad = actividadService.createActividad(actividad);

        assertNotNull(savedActividad.getId(), "La actividad guardada debe tener un ID.");
        assertEquals("Excursión", savedActividad.getNombre());
        assertEquals("Caminata por la montaña", savedActividad.getDescripcion());
        assertEquals("España", savedActividad.getPais());
        assertEquals("Madrid", savedActividad.getCiudad());
        assertEquals(10.0, savedActividad.getPrecio());
        assertEquals(LocalDate.parse("2026-05-11"), savedActividad.getFecha());
    }

    // Test para obtener una actividad por nombre
    @Test
    public void testGetActividadByNombre() {

        Actividad actividad1 = new Actividad();
        actividad1.setNombre("Excursión");
        actividad1.setDescripcion("Caminata por la montaña");
        actividad1.setPais("España");
        actividad1.setCiudad("Madrid");
        actividad1.setPrecio(10.0);
        actividad1.setFecha(LocalDate.parse("2026-05-11"));

        Actividad actividad2 = new Actividad();
        actividad2.setNombre("Excursión");
        actividad2.setDescripcion("Caminata por la montaña");
        actividad2.setPais("España");
        actividad2.setCiudad("Madrid");
        actividad2.setPrecio(10.0);
        actividad2.setFecha(LocalDate.parse("2026-05-11"));

        actividadService.createActividad(actividad1);
        actividadService.createActividad(actividad2);

        List<Actividad> foundNombre = actividadService.getActividadesByNombre("Excursión");

        assertNotNull(foundNombre);
        assertEquals(2, foundNombre.size());

    }

    // Test para obtener una actividad por pais
    @Test
    public void testGetActividadesByPaisPaginable() {

        actividadRepository.deleteAll();

        Actividad actividad1 = new Actividad();
        actividad1.setNombre("Excursión");
        actividad1.setDescripcion("Caminata por la montaña");
        actividad1.setPais("Colombia");
        actividad1.setCiudad("Bogotá");
        actividad1.setPrecio(10.0);
        actividad1.setFecha(LocalDate.of(2026, 5, 11));

        Actividad actividad2 = new Actividad();
        actividad2.setNombre("Tour");
        actividad2.setDescripcion("Recorrido por la ciudad");
        actividad2.setPais("Colombia");
        actividad2.setCiudad("Medellín");
        actividad2.setPrecio(15.0);
        actividad2.setFecha(LocalDate.of(2026, 4, 10));

        actividadService.createActividad(actividad1);
        actividadService.createActividad(actividad2);

        Page<Actividad> page = actividadService.getActividadesByPaisPaginable("Colombia", 0, 1, "nombre");

        // Verificaciones
        assertNotNull(page);
        assertEquals(2, page.getTotalElements());
        assertEquals(2, page.getTotalPages());
        assertEquals(1, page.getContent().size());
        assertEquals("Bogotá", page.getContent().get(0).getCiudad());

    }

    // Test para obtener una actividad por fecha
    @Test
    public void testGetActividadByFecha() {

        Actividad actividad1 = new Actividad();
        actividad1.setNombre("Excursión");
        actividad1.setDescripcion("Caminata por la montaña");
        actividad1.setPais("España");
        actividad1.setCiudad("Madrid");
        actividad1.setPrecio(10.0);
        actividad1.setFecha(LocalDate.parse("2027-12-25"));

        Actividad actividad2 = new Actividad();
        actividad2.setNombre("Excursión");
        actividad2.setDescripcion("Caminata por la montaña");
        actividad2.setPais("Colombia");
        actividad2.setCiudad("Bogota");
        actividad2.setPrecio(10.0);
        actividad2.setFecha(LocalDate.parse("2026-05-11"));

        actividadService.createActividad(actividad1);
        actividadService.createActividad(actividad2);

        // Se tiene que hacer un parse debido a que espera un LocalDate
        Optional<Actividad> foundFecha = actividadService.getActividadesByFecha(LocalDate.parse("2027-12-25"));

        assertNotNull(foundFecha);

    }
}


