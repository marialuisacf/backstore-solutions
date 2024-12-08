package backsolutions.vista.gui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.cell.PropertyValueFactory;
import backsolutions.modelo.*;

public class VistaSociosGUI {
    private final VBox vistaPrincipal;
    private final TextField numSocioField;
    private final TextField nombreField;
    private final ComboBox<String> tipoSocioBox;
    private final TextField nifField;
    private final TextField numSocioTutorField;
    private final ComboBox<String> seguroBox;
    private final TextField precioSeguroField;
    private final Button añadirSocioButton;

    private final TableView<Socio> tablaSocios;
    private final ComboBox<String> filtroTipoSocioBox;
    private final Button mostrarTodosButton;

    private final TextField modificarNumSocioField;
    private final ComboBox<String> nuevoTipoSeguroBox;
    private final TextField nuevoPrecioSeguroField;
    private final Button modificarSeguroButton;

    private final TextField eliminarNumSocioField;
    private final Button eliminarSocioButton;

    private final TextField facturaNumSocioField;
    private final Button mostrarFacturaButton;
    private final TextArea facturaTextArea;

    public VistaSociosGUI() {
        vistaPrincipal = new VBox(10);
        vistaPrincipal.setPadding(new Insets(10));

        // Sección "Añadir Socio"
        Label añadirSocioLabel = new Label("Añadir Socio");
        añadirSocioLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16)); //utilizamos el metodo Font.font con BOLD para definir la fuente en negrita
        HBox añadirSocioBox = new HBox(10);

        numSocioField = new TextField();
        numSocioField.setPromptText("Num. Socio");

        nombreField = new TextField();
        nombreField.setPromptText("Nombre");

        tipoSocioBox = new ComboBox<>();
        tipoSocioBox.getItems().addAll("Estandar", "Federado", "Infantil");
        tipoSocioBox.setPromptText("Tipo Socio");

        nifField = new TextField();
        nifField.setPromptText("NIF (Solo Estandar/Federado)");

        numSocioTutorField = new TextField();
        numSocioTutorField.setPromptText("Num. Socio Tutor (Infantil)");

        seguroBox = new ComboBox<>();
        seguroBox.getItems().addAll("basico", "completo");
        seguroBox.setPromptText("Seguro (Estandar)");

        precioSeguroField = new TextField();
        precioSeguroField.setPromptText("Precio Seguro");

        añadirSocioButton = new Button("Añadir Socio");

        añadirSocioBox.getChildren().addAll(numSocioField, nombreField, tipoSocioBox, nifField, numSocioTutorField, seguroBox, precioSeguroField, añadirSocioButton);

        // Sección "Mostrar Socios"
        Label mostrarSociosLabel = new Label("Mostrar Socios");
        mostrarSociosLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        filtroTipoSocioBox = new ComboBox<>();
        filtroTipoSocioBox.getItems().addAll("Todos", "Estandar", "Federado", "Infantil");
        filtroTipoSocioBox.setPromptText("Filtrar por tipo");

        mostrarTodosButton = new Button("Mostrar Selección");

        tablaSocios = new TableView<>();

        // Agregar las columnas a la tabla
        // Columna "Num. Socio"
        TableColumn<Socio, Integer> numSocioCol = new TableColumn<>("Num. Socio");
        numSocioCol.setCellValueFactory(new PropertyValueFactory<>("numSocio"));

        // Columna "Nombre"
        TableColumn<Socio, String> nombreCol = new TableColumn<>("Nombre");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        // Columna "Tipo" personalizada
        TableColumn<Socio, String> tipoCol = new TableColumn<>("Tipo");
        tipoCol.setCellValueFactory(cellData -> {
            Socio socio = cellData.getValue();
            String tipo = socio instanceof Estandar ? "Estandar" :
                    socio instanceof Federado ? "Federado" :
                            socio instanceof Infantil ? "Infantil" : "Desconocido";
            return new javafx.beans.property.SimpleStringProperty(tipo);
        });

        // Columna "Seguro Tipo" (solo para Socios Estandar)
        TableColumn<Socio, String> seguroTipoCol = new TableColumn<>("Tipo Seguro");
        seguroTipoCol.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Estandar) {
                Estandar estandar = (Estandar) cellData.getValue();
                return new javafx.beans.property.SimpleStringProperty(estandar.getSeguro().getTipo());
            }
            return new javafx.beans.property.SimpleStringProperty(""); // Vacío para otros tipos de socios
        });

        // Columna "Precio Seguro" (solo para Socios Estandar)
        TableColumn<Socio, Double> precioSeguroCol = new TableColumn<>("Precio Seguro");
        precioSeguroCol.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Estandar) {
                Estandar estandar = (Estandar) cellData.getValue();
                return new javafx.beans.property.SimpleObjectProperty<>(estandar.getSeguro().getPrecio());
            }
            return new javafx.beans.property.SimpleObjectProperty<>(null); // Null para otros tipos
        });

        // Columna "Código Federación" (solo para Socios Federados)
        TableColumn<Socio, String> codigoFederacionCol = new TableColumn<>("Código Federación");
        codigoFederacionCol.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Federado) {
                Federado federado = (Federado) cellData.getValue();
                return new javafx.beans.property.SimpleStringProperty(federado.getFederacion().getCodigo());
            }
            return new javafx.beans.property.SimpleStringProperty("");
        });

        // Columna "Nombre Federación" (solo para Socios Federados)
        TableColumn<Socio, String> nombreFederacionCol = new TableColumn<>("Nombre Federación");
        nombreFederacionCol.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Federado) {
                Federado federado = (Federado) cellData.getValue();
                return new javafx.beans.property.SimpleStringProperty(federado.getFederacion().getNombre());
            }
            return new javafx.beans.property.SimpleStringProperty("");
        });

        // Columna "Num. Socio Tutor" (solo para Socios Infantiles)
        TableColumn<Socio, String> numSocioTutorCol = new TableColumn<>("Num. Socio Tutor");
        numSocioTutorCol.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Infantil) {
                Infantil infantil = (Infantil) cellData.getValue();
                return new javafx.beans.property.SimpleStringProperty(infantil.getNumSocioTutor());
            }
            return new javafx.beans.property.SimpleStringProperty("");
        });

        // Configura el ancho de las columnas
        numSocioCol.setMinWidth(100);
        nombreCol.setMinWidth(150);
        tipoCol.setMinWidth(100);
        seguroTipoCol.setMinWidth(120);
        precioSeguroCol.setMinWidth(120);
        codigoFederacionCol.setMinWidth(150);
        nombreFederacionCol.setMinWidth(150);
        numSocioTutorCol.setMinWidth(150);

        // Agregar columnas a la tabla
        tablaSocios.getColumns().addAll(
                numSocioCol, nombreCol, tipoCol,
                seguroTipoCol, precioSeguroCol,
                codigoFederacionCol, nombreFederacionCol,
                numSocioTutorCol
        );




        VBox mostrarSociosBox = new VBox(10, filtroTipoSocioBox, mostrarTodosButton, tablaSocios);

        // Sección "Modificar Tipo de Seguro"
        Label modificarSeguroLabel = new Label("Modificar Tipo de Seguro");
        modificarSeguroLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        modificarNumSocioField = new TextField();
        modificarNumSocioField.setPromptText("Num. Socio");

        nuevoTipoSeguroBox = new ComboBox<>();
        nuevoTipoSeguroBox.getItems().addAll("basico", "completo");
        nuevoTipoSeguroBox.setPromptText("Nuevo Tipo Seguro");

        nuevoPrecioSeguroField = new TextField();
        nuevoPrecioSeguroField.setPromptText("Nuevo Precio Seguro");

        modificarSeguroButton = new Button("Modificar Seguro");

        VBox modificarSeguroBox = new VBox(10, modificarNumSocioField, nuevoTipoSeguroBox, nuevoPrecioSeguroField, modificarSeguroButton);

        // Sección "Eliminar Socio"
        Label eliminarSocioLabel = new Label("Eliminar Socio");
        eliminarSocioLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        eliminarNumSocioField = new TextField();
        eliminarNumSocioField.setPromptText("Num. Socio");

        eliminarSocioButton = new Button("Eliminar Socio");

        VBox eliminarSocioBox = new VBox(10, eliminarNumSocioField, eliminarSocioButton);

        // Sección "Mostrar Factura"
        Label mostrarFacturaLabel = new Label("Mostrar Factura");
        mostrarFacturaLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        facturaNumSocioField = new TextField();
        facturaNumSocioField.setPromptText("Num. Socio");

        mostrarFacturaButton = new Button("Mostrar Factura");

        facturaTextArea = new TextArea();
        facturaTextArea.setEditable(false);

        VBox mostrarFacturaBox = new VBox(10, facturaNumSocioField, mostrarFacturaButton, facturaTextArea);

        // Agregar todas las secciones al VBox principal
        vistaPrincipal.getChildren().addAll(
                añadirSocioLabel, añadirSocioBox,
                mostrarSociosLabel, mostrarSociosBox,
                modificarSeguroLabel, modificarSeguroBox,
                eliminarSocioLabel, eliminarSocioBox,
                mostrarFacturaLabel, mostrarFacturaBox
        );
    }

    public VBox getVistaPrincipal() {
        return vistaPrincipal;
    }

    // Getters para los campos necesarios
    public TextField getNumSocioField() { return numSocioField; }
    public TextField getNombreField() { return nombreField; }
    public ComboBox<String> getTipoSocioBox() { return tipoSocioBox; }
    public TextField getNifField() { return nifField; }
    public TextField getNumSocioTutorField() { return numSocioTutorField; }
    public ComboBox<String> getSeguroBox() { return seguroBox; }
    public TextField getPrecioSeguroField() { return precioSeguroField; }
    public Button getAñadirSocioButton() { return añadirSocioButton; }
    public TableView<Socio> getTablaSocios() { return tablaSocios; }
    public ComboBox<String> getFiltroTipoSocioBox() { return filtroTipoSocioBox; }
    public Button getMostrarTodosButton() { return mostrarTodosButton; }
    public TextField getModificarNumSocioField() { return modificarNumSocioField; }
    public ComboBox<String> getNuevoTipoSeguroBox() { return nuevoTipoSeguroBox; }
    public TextField getNuevoPrecioSeguroField() { return nuevoPrecioSeguroField; }
    public Button getModificarSeguroButton() { return modificarSeguroButton; }
    public TextField getEliminarNumSocioField() { return eliminarNumSocioField; }
    public Button getEliminarSocioButton() { return eliminarSocioButton; }
    public TextField getFacturaNumSocioField() { return facturaNumSocioField; }
    public Button getMostrarFacturaButton() { return mostrarFacturaButton; }
    public TextArea getFacturaTextArea() { return facturaTextArea; }
}
