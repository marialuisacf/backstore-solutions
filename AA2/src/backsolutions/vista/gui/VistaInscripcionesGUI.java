package backsolutions.vista.gui;

import backsolutions.modelo.Inscripcion;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VistaInscripcionesGUI {
    private final VBox vistaPrincipal;

    // Sección Añadir Inscripción
    private final TextField numInscripcionField;
    private final TextField numSocioField;
    private final TextField codigoExcursionField;
    private final DatePicker fechaInscripcionPicker;
    private final Button añadirInscripcionButton;

    // Sección Mostrar Inscripciones
    private final TableView<Inscripcion> tablaInscripciones;
    private final Button mostrarInscripcionesButton;

    // Sección Cancelar Inscripción
    private final TextField cancelarNumInscripcionField;
    private final Button cancelarInscripcionButton;

    public VistaInscripcionesGUI() {
        vistaPrincipal = new VBox(10);
        vistaPrincipal.setPadding(new Insets(10));

        // Sección "Añadir Inscripción"
        Label añadirInscripcionLabel = new Label("Añadir Inscripción");
        añadirInscripcionLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        HBox añadirInscripcionBox = new HBox(10);

        numInscripcionField = new TextField();
        numInscripcionField.setPromptText("Número de Inscripción");

        numSocioField = new TextField();
        numSocioField.setPromptText("Número de Socio");

        codigoExcursionField = new TextField();
        codigoExcursionField.setPromptText("Código de Excursión");

        fechaInscripcionPicker = new DatePicker();
        fechaInscripcionPicker.setPromptText("Fecha de Inscripción");

        añadirInscripcionButton = new Button("Añadir Inscripción");

        añadirInscripcionBox.getChildren().addAll(numInscripcionField, numSocioField, codigoExcursionField, fechaInscripcionPicker, añadirInscripcionButton);

        // Sección "Mostrar Inscripciones"
        Label mostrarInscripcionesLabel = new Label("Mostrar Inscripciones");
        mostrarInscripcionesLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        tablaInscripciones = new TableView<>();
        mostrarInscripcionesButton = new Button("Mostrar Inscripciones"); // Asignar el botón correctamente aquí

        VBox mostrarInscripcionesBox = new VBox(10, mostrarInscripcionesButton, tablaInscripciones);

        // Sección "Cancelar Inscripción"
        Label cancelarInscripcionLabel = new Label("Cancelar Inscripción");
        cancelarInscripcionLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        HBox cancelarInscripcionBox = new HBox(10);

        cancelarNumInscripcionField = new TextField();
        cancelarNumInscripcionField.setPromptText("Número de Inscripción");

        cancelarInscripcionButton = new Button("Cancelar Inscripción");

        cancelarInscripcionBox.getChildren().addAll(cancelarNumInscripcionField, cancelarInscripcionButton);

        // Agregar todas las secciones al VBox principal
        vistaPrincipal.getChildren().addAll(
                añadirInscripcionLabel, añadirInscripcionBox,
                mostrarInscripcionesLabel, mostrarInscripcionesBox,
                cancelarInscripcionLabel, cancelarInscripcionBox
        );
    }

    public VBox getVistaPrincipal() {
        return vistaPrincipal;
    }

    // Getters para acceder a los elementos desde el controlador
    public TextField getNumInscripcionField() { return numInscripcionField; }
    public TextField getNumSocioField() { return numSocioField; }
    public TextField getCodigoExcursionField() { return codigoExcursionField; }
    public DatePicker getFechaInscripcionPicker() { return fechaInscripcionPicker; }
    public Button getAñadirInscripcionButton() { return añadirInscripcionButton; }
    public TableView<Inscripcion> getTablaInscripciones() { return tablaInscripciones; }
    public Button getMostrarInscripcionesButton() { return mostrarInscripcionesButton; }
    public TextField getCancelarNumInscripcionField() { return cancelarNumInscripcionField; }
    public Button getCancelarInscripcionButton() { return cancelarInscripcionButton; }
}
