package backsolutions;

import backsolutions.controlador.*;
import backsolutions.modelo.*;
import backsolutions.vista.*;

import java.util.ArrayList; // Importar ArrayList

public class Main {
    public static void main(String[] args) {
        // Crear listas vacías para Excursion e Inscripcion
        ArrayList<Excursion> excursiones = new ArrayList<>();
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();

        // Pasar listas vacías al constructor
        ControladorExcursion controladorExcursion = new ControladorExcursion(excursiones);
        ControladorSocio controladorSocio = new ControladorSocio();
        ControladorInscripcion controladorInscripcion = new ControladorInscripcion(inscripciones, excursiones);

        // Crear la vista principal
        VistaPrincipal vista = new VistaPrincipal(controladorExcursion, controladorSocio, controladorInscripcion);
        vista.mostrarMenu();  // Iniciamos el menú desde la vista VistaPrincipal
    }
}
