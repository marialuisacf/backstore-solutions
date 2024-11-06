package backsolutions;

import backsolutions.controlador.*;
import backsolutions.modelo.*;
import backsolutions.vista.*;

import java.util.ArrayList; // Importar ArrayList

public class Main {
    public static void main(String[] args) {

        // Crear el controlador de inscripciones primero
        ControladorInscripcion controladorInscripcion = new ControladorInscripcion();

        // Luego, crear el controlador de socios y pasar el controlador de inscripciones
        ControladorSocio controladorSocio = new ControladorSocio(controladorInscripcion);

        // Finalmente, crear el controlador de excursiones
        ControladorExcursion controladorExcursion = new ControladorExcursion();

        // Crear la vista principal
        VistaPrincipal vista = new VistaPrincipal(controladorExcursion, controladorSocio, controladorInscripcion);
        vista.mostrarMenu();  // Iniciamos el menú desde la vista VistaPrincipal
    }
}
