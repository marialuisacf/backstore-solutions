package backsolutions.vista;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import backsolutions.controlador.gui.ControladorExcursionesGUI;
import backsolutions.vista.gui.VistaExcursionesGUI;

public class MainMenu extends Application {
    //La clase extiende Application de JavaFX permitiendo inicializar y mostrar la interfaz gráfica.
    @Override
    public void start(Stage primaryStage) {
        // Creamos el TabPane para manejar las pestañas de la interfaz
        TabPane tabPane = new TabPane();

        // Gestión de Excursiones
        Tab gestionExcursionesTab = new Tab("Gestión de Excursiones");
        VistaExcursionesGUI vistaExcursiones = new VistaExcursionesGUI(primaryStage); // Pasa el Stage como parámetro
        ControladorExcursionesGUI controladorExcursiones = new ControladorExcursionesGUI(vistaExcursiones); // Asociar el controlador
        gestionExcursionesTab.setContent(vistaExcursiones.getVistaPrincipal()); // Usa el VBox principal
        gestionExcursionesTab.setClosable(false); // Evita que se pueda cerrar la pestaña

        // Gestión de Socios (placeholder por ahora)
        Tab gestionSociosTab = new Tab("Gestión de Socios");
        gestionSociosTab.setContent(new javafx.scene.control.Label("Gestión de Socios en proceso de creación aún..."));
        gestionSociosTab.setClosable(false);

        // Gestión de Inscripciones (placeholder por ahora)
        Tab gestionInscripcionesTab = new Tab("Gestión de Inscripciones");
        gestionInscripcionesTab.setContent(new javafx.scene.control.Label("Gestión de Inscripciones en proceso de creación aún..."));
        gestionInscripcionesTab.setClosable(false);

        // Agregamos las pestañas al TabPane
        tabPane.getTabs().addAll(gestionExcursionesTab, gestionSociosTab, gestionInscripcionesTab);

        // La escena principal
        Scene scene = new Scene(tabPane, 800, 600);
        primaryStage.setTitle("¡Bienvenidos a Senderos y Montañas! - Menú Principal:");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        //Este es el punto de entrada, el metodo main() llama a launch(args) que iniciliza JavaFX y muestra la ventana.
    }
}
