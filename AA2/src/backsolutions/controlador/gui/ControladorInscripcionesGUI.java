package backsolutions.controlador.gui;

import backsolutions.modelo.Inscripcion;
import backsolutions.modelo.dao.InscripcionDAO;
import backsolutions.modelo.dao.InscripcionDAOImpl;
import backsolutions.modelo.Socio;
import backsolutions.modelo.Excursion;
import backsolutions.modelo.dao.SocioDAO;
import backsolutions.modelo.dao.SocioDAOImpl;
import backsolutions.modelo.dao.ExcursionDAO;
import backsolutions.modelo.dao.ExcursionDAOImpl;
import backsolutions.vista.gui.VistaInscripcionesGUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.time.LocalDate;

public class ControladorInscripcionesGUI {
    private final VistaInscripcionesGUI vista; //La interfaz gráfica
    private final InscripcionDAO inscripcionDAO;
    private final SocioDAO socioDAO;
    private final ExcursionDAO excursionDAO;
    private final ObservableList<Inscripcion> listaInscripciones; //cargar y mostrar inscripciones en la tabla

    public ControladorInscripcionesGUI(VistaInscripcionesGUI vista) {
        this.vista = vista;
        this.inscripcionDAO = new InscripcionDAOImpl();
        this.socioDAO = new SocioDAOImpl();
        this.excursionDAO = new ExcursionDAOImpl();
        this.listaInscripciones = FXCollections.observableArrayList();

        inicializarEventos();
        cargarInscripciones();
    }

    // Inicializar los eventos de los botones
    private void inicializarEventos() {
        vista.getAñadirInscripcionButton().setOnAction(e -> añadirInscripcion());
        vista.getMostrarInscripcionesButton().setOnAction(e -> cargarInscripciones());
        vista.getCancelarInscripcionButton().setOnAction(e -> cancelarInscripcion());
    }

    // Cargar todas las inscripciones en la tabla recuperando las inscripciones de la BD y las muestra en la tabla
    private void cargarInscripciones() {
        try {
            listaInscripciones.setAll(inscripcionDAO.listarInscripciones());
            vista.getTablaInscripciones().setItems(listaInscripciones);
        } catch (Exception e) {
            mostrarError("Error al cargar inscripciones: " + e.getMessage());
        }
    }

    // Añadir una nueva inscripción: obtiene los datos indicados por el usuario, verifica si el socio y la excursion estan en la BD y, crea la inscripcion y la guarda
    private void añadirInscripcion() {
        try {
            String numInscripcion = vista.getNumInscripcionField().getText();
            int numSocio = Integer.parseInt(vista.getNumSocioField().getText());
            String codigoExcursion = vista.getCodigoExcursionField().getText();
            LocalDate fechaInscripcion = vista.getFechaInscripcionPicker().getValue();

            //Solicitar tipo de seguro y precio del seguro
            String tipoSeguro = "Ninguno"; // Valor predeterminado
            double precioSeguro = 0.0;

            // Verificar si el socio es "Estandar" para asignar seguro
            Socio socio = socioDAO.buscarSocio(numSocio);
            Excursion excursion = excursionDAO.buscarExcursion(codigoExcursion);

            if (socio != null && excursion != null) {
                if (socio instanceof backsolutions.modelo.Estandar) {
                    tipoSeguro = ((backsolutions.modelo.Estandar) socio).getSeguro().getTipo();
                    precioSeguro = ((backsolutions.modelo.Estandar) socio).getSeguro().getPrecio();
                }

                // Crear la inscripción con los valores completos
                Inscripcion inscripcion = new Inscripcion(numInscripcion, socio, excursion, fechaInscripcion, tipoSeguro, precioSeguro);

                // Guardar en la base de datos
                inscripcionDAO.guardarInscripcion(inscripcion);
                listaInscripciones.add(inscripcion);
                limpiarCampos();
            } else {
                mostrarError("Socio o Excursión no encontrados.");
            }
        } catch (Exception e) {
            mostrarError("Error al añadir inscripción: " + e.getMessage());
        }
    }


    // Cancelar una inscripción: elimina una inscripcion a partir del numInscripcion indicado por el usuario y actualiza la tabla con los cambios
    private void cancelarInscripcion() {
        try {
            String numInscripcion = vista.getCancelarNumInscripcionField().getText();
            inscripcionDAO.eliminarInscripcion(numInscripcion);
            cargarInscripciones(); // Volver a cargar las inscripciones
            vista.getCancelarNumInscripcionField().clear();
        } catch (Exception e) {
            mostrarError("Error al cancelar inscripción: " + e.getMessage());
        }
    }

    // Limpiar los campos de entrada después de añadir una inscripción
    private void limpiarCampos() {
        vista.getNumInscripcionField().clear();
        vista.getNumSocioField().clear();
        vista.getCodigoExcursionField().clear();
        vista.getFechaInscripcionPicker().setValue(null);
    }

    //Muestra errores con un cuadro de dialogo Alert
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
