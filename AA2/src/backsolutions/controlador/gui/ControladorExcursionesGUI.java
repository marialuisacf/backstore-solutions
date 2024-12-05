package backsolutions.controlador.gui;

import backsolutions.modelo.Excursion;
import backsolutions.modelo.dao.ExcursionDAO;
import backsolutions.modelo.dao.ExcursionDAOImpl;
import backsolutions.vista.gui.VistaExcursionesGUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class ControladorExcursionesGUI {
    private final VistaExcursionesGUI vista;
    private final ExcursionDAO excursionDAO;
    private final ObservableList<Excursion> excursiones;

    public ControladorExcursionesGUI(VistaExcursionesGUI vista) {
        this.vista = vista;
        this.excursionDAO = new ExcursionDAOImpl();
        this.excursiones = FXCollections.observableArrayList();

        inicializarEventos();
        cargarExcursiones();
    }

    private void inicializarEventos() {
        vista.getAñadirButton().setOnAction(e -> añadirExcursion());
        vista.getFiltrarButton().setOnAction(e -> filtrarExcursiones());
    }

    private void cargarExcursiones() {
        try {
            excursiones.setAll(excursionDAO.listarExcursiones());
            vista.getExcursionesTable().setItems(excursiones);
        } catch (Exception e) {
            System.err.println("Error al cargar excursiones: " + e.getMessage());
        }
    }

    private void añadirExcursion() {
        try {
            String codigo = vista.getCodigoField().getText();
            String descripcion = vista.getDescripcionField().getText();
            LocalDate fecha = vista.getFechaField().getValue();
            int numDias = Integer.parseInt(vista.getNumDiasField().getText());
            double precio = Double.parseDouble(vista.getPrecioField().getText());

            Excursion excursion = new Excursion(codigo, descripcion, fecha, numDias, precio);
            excursionDAO.guardarExcursion(excursion);
            excursiones.add(excursion);

            vista.getCodigoField().clear();
            vista.getDescripcionField().clear();
            vista.getFechaField().setValue(null);
            vista.getNumDiasField().clear();
            vista.getPrecioField().clear();
        } catch (Exception e) {
            System.err.println("Error al añadir excursión: " + e.getMessage());
        }
    }

    private void filtrarExcursiones() {
        LocalDate inicio = vista.getFiltroFechaInicio().getValue();
        LocalDate fin = vista.getFiltroFechaFin().getValue();

        try {
            ObservableList<Excursion> filtradas = FXCollections.observableArrayList(
                    excursionDAO.filtrarExcursionesPorFechas(inicio, fin)
            );
            vista.getExcursionesTable().setItems(filtradas);
        } catch (Exception e) {
            System.err.println("Error al filtrar excursiones: " + e.getMessage());
        }
    }
}
