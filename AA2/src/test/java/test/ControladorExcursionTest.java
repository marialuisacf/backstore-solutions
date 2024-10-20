package test;

import controladorTest.*;
import modeloTest.Excursion;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ControladorExcursionTest {

    private ControladorExcursion controladorExcursion;
    private List<Excursion> excursiones;

    @BeforeEach
    public void setUp() {
        excursiones = new ArrayList<>(); //inicializa la lista de excursiones vacía antes de cada test
        controladorExcursion = new ControladorExcursion(excursiones); //pasa la lista de excursiones al controlador
    }
    //Test 1: para verificar que se añade correctamente una nueva excursion
    @Test
    public void addExcursion_nuevaExcursion_addCorrectamente() throws ControladorExcepcion {
        //Se crean datos de prueba
        Excursion excursion = new Excursion("EXC001", "Excursión a la montaña", LocalDate.of(2024, 11, 20), 1, 50.0); // Añadir el parámetro de duracion

        //Se ejecuta el metodo bajo prueba
        controladorExcursion.addExcursion(excursion);

        //se verifica que la excursión ha sido añadida a la lista
        assertEquals(1, excursiones.size()); // Debe haber 1 excursión en la lista
        assertEquals("EXC001", excursiones.get(0).getCodigo()); // Verifica que el código de la excursión es correcto
        assertTrue(true);
    }

    //Test 2: para verificar que no se puede añadir una excursión duplicada
    @Test
    public void addExcursion_conExcursionDuplicada_lanzaExcepcion() throws ControladorExcepcion {
        //se crea una excursión de prueba
        Excursion excursion = new Excursion("EXC002", "Excursión a la playa", LocalDate.of(2024, 12, 15), 1, 75.0);
        controladorExcursion.addExcursion(excursion); //añadir la excursión

        //se crea una excursión duplicada con el mismo código
        Excursion excursionDuplicada = new Excursion("EXC002", "Excursión repetida", LocalDate.of(2024, 12, 16), 1, 80.0);

        //se verifica que se lanza la excepción al intentar añadir la duplicada
        ControladorExcepcion excepcion = assertThrows(ControladorExcepcion.class, () -> controladorExcursion.addExcursion(excursionDuplicada));

        //verificamos el mensaje de la excepción
        assertEquals("La excursión con código EXC002 ya existe.", excepcion.getMessage());
        assertTrue(true);
    }

}
