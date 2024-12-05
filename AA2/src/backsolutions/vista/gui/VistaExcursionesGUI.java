package backsolutions.vista.gui;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import backsolutions.modelo.Excursion;

import java.time.LocalDate;

public class VistaExcursionesGUI {
    private final VBox vistaPrincipal; //Layout principal de la vista
    private final TableView<Excursion> excursionesTable;
    private final TextField codigoField;
    private final TextField descripcionField;
    private final DatePicker fechaField;
    private final TextField numDiasField;
    private final TextField precioField;
    private final Button añadirButton;
    private final DatePicker filtroFechaInicio;
    private final DatePicker filtroFechaFin;
    private final Button filtrarButton;

    public VistaExcursionesGUI(Stage stage) {
        vistaPrincipal = new VBox(10);
        vistaPrincipal.setSpacing(10);

        //Primer título de "Añadir Excursión" que es la primera opción de nuestro menu
        Label tituloAñadir = new Label("Añadir Excursión");
        tituloAñadir.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Formulario para añadir excursiones
        Label codigoLabel = new Label("Código:");
        codigoField = new TextField();
        Label descripcionLabel = new Label("Descripción:");
        descripcionField = new TextField();
        Label fechaLabel = new Label("Fecha:");
        fechaField = new DatePicker();
        Label numDiasLabel = new Label("Número de días:");
        numDiasField = new TextField();
        Label precioLabel = new Label("Precio:");
        precioField = new TextField();
        añadirButton = new Button("Añadir Excursión");

        HBox añadirBox = new HBox(10, codigoLabel, codigoField, descripcionLabel, descripcionField, fechaLabel, fechaField, numDiasLabel, numDiasField, precioLabel, precioField, añadirButton);
        añadirBox.setSpacing(10);

        //Segundo título de "Mostrar Excursiones"
        Label tituloMostrar = new Label("Mostrar Excursiones");
        tituloMostrar.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        //Tabla de excursiones
        excursionesTable = new TableView<>();
        TableColumn<Excursion, String> codigoColumn = new TableColumn<>("Código");
        codigoColumn.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        TableColumn<Excursion, String> descripcionColumn = new TableColumn<>("Descripción");
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        TableColumn<Excursion, LocalDate> fechaColumn = new TableColumn<>("Fecha");
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        TableColumn<Excursion, Integer> numDiasColumn = new TableColumn<>("Número de días");
        numDiasColumn.setCellValueFactory(new PropertyValueFactory<>("numDias"));

        TableColumn<Excursion, Double> precioColumn = new TableColumn<>("Precio");
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precioInscripcion"));

        excursionesTable.getColumns().addAll(codigoColumn, descripcionColumn, fechaColumn, numDiasColumn, precioColumn);
        excursionesTable.setPrefHeight(300);

        //Filtro de fechas
        Label filtroLabel = new Label("Filtrar entre fechas:");
        filtroFechaInicio = new DatePicker();
        filtroFechaFin = new DatePicker();
        filtrarButton = new Button("Filtrar");

        HBox filtroBox = new HBox(10, filtroLabel, filtroFechaInicio, filtroFechaFin, filtrarButton);
        filtroBox.setSpacing(10);

        //Añadir elementos al layout principal
        vistaPrincipal.getChildren().addAll(tituloAñadir, añadirBox, tituloMostrar, excursionesTable, filtroBox);
    }

    // Getter para el layout principal
    public VBox getVistaPrincipal() {
        return vistaPrincipal;
    }

    // Getters para los controles
    public TableView<Excursion> getExcursionesTable() {
        return excursionesTable;
    }

    public TextField getCodigoField() {
        return codigoField;
    }

    public TextField getDescripcionField() {
        return descripcionField;
    }

    public DatePicker getFechaField() {
        return fechaField;
    }

    public TextField getNumDiasField() {
        return numDiasField;
    }

    public TextField getPrecioField() {
        return precioField;
    }

    public Button getAñadirButton() {
        return añadirButton;
    }

    public DatePicker getFiltroFechaInicio() {
        return filtroFechaInicio;
    }

    public DatePicker getFiltroFechaFin() {
        return filtroFechaFin;
    }

    public Button getFiltrarButton() {
        return filtrarButton;
    }
}
