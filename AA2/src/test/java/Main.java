import controladorTest.ControladorExcursion;
import controladorTest.ControladorInscripcion;
import controladorTest.ControladorSocio;
import modeloTest.Excursion;
import modeloTest.Inscripcion;
import vistaTest.VistaPrincipal;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Crear listas vacías para Excursion e Inscripcion
        ArrayList<Excursion> excursiones = new ArrayList<>();
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();

        // Crear el controlador de inscripciones primero
        ControladorInscripcion controladorInscripcion = new ControladorInscripcion(inscripciones, excursiones);

        // Luego, crear el controlador de socios y pasar el controlador de inscripciones
        ControladorSocio controladorSocio = new ControladorSocio(controladorInscripcion);

        // Finalmente, crear el controlador de excursiones
        ControladorExcursion controladorExcursion = new ControladorExcursion(excursiones);

        // Crear la vista principal
        VistaPrincipal vista = new VistaPrincipal(controladorExcursion, controladorSocio, controladorInscripcion);
        vista.mostrarMenu();  // Iniciamos el menú desde la vista VistaPrincipal
    }
}
