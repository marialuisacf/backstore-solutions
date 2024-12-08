package backsolutions.vista;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import backsolutions.controlador.gui.ControladorExcursionesGUI;
import backsolutions.vista.gui.VistaExcursionesGUI;

import backsolutions.controlador.gui.ControladorSociosGUI;
import backsolutions.vista.gui.VistaSociosGUI;

import backsolutions.controlador.gui.ControladorInscripcionesGUI;
import backsolutions.vista.gui.VistaInscripcionesGUI;

public class MainMenu extends Application {
    @Override
    public void start(Stage primaryStage) {
        //Creamos el TabPane para manejar las pestañas
        TabPane tabPane = new TabPane();

        //Pestaña de Gestión Excursiones
        Tab gestionExcursionesTab = new Tab("Gestión de Excursiones");
        VistaExcursionesGUI vistaExcursiones = new VistaExcursionesGUI(primaryStage);
        ControladorExcursionesGUI controladorExcursiones = new ControladorExcursionesGUI(vistaExcursiones);
        gestionExcursionesTab.setContent(vistaExcursiones.getVistaPrincipal());
        gestionExcursionesTab.setClosable(false);

        //Pestaña de Gestión Socios
        Tab gestionSociosTab = new Tab("Gestión de Socios");
        VistaSociosGUI vistaSocios = new VistaSociosGUI();
        ControladorSociosGUI controladorSocios = new ControladorSociosGUI(vistaSocios);
        gestionSociosTab.setContent(vistaSocios.getVistaPrincipal());
        gestionSociosTab.setClosable(false);

        //Pestaña de Gestión Inscripciones
        Tab gestionInscripcionesTab = new Tab("Gestión de Inscripciones");
        VistaInscripcionesGUI vistaInscripciones = new VistaInscripcionesGUI();
        ControladorInscripcionesGUI controladorInscripciones = new ControladorInscripcionesGUI(vistaInscripciones);
        gestionInscripcionesTab.setContent(vistaInscripciones.getVistaPrincipal());
        gestionInscripcionesTab.setClosable(false);

        //Agregamos todas las pestañas al TabPane
        tabPane.getTabs().addAll(
                gestionExcursionesTab,
                gestionSociosTab,
                gestionInscripcionesTab
        );

        //Ventana principal
        Scene scene = new Scene(tabPane, 800, 600);
        primaryStage.setTitle("¡Bienvenidos a Senderos y Montañas! - Menú Principal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
