package backsolutions.vista;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainMenu extends Application {
    //La clase extiende Application de JavaFX permitiendo inicializar y mostrar la interfaz gráfica.
    @Override
    public void start(Stage primaryStage) {
        //Creamos el TabPane para manejar las pestañas de la interfaz
        TabPane tabPane = new TabPane();

        //Creamos las pestañas
        Tab tabExcursiones = new Tab("Gestión Excursiones");
        Tab tabSocios = new Tab("Gestión Socios");
        Tab tabInscripciones = new Tab("Gestión Inscripciones");

        //Agregamos las pestañas al TabPane
        tabPane.getTabs().addAll(tabExcursiones, tabSocios, tabInscripciones);

        //Bloqueamos que se puedan cerrar las pestañas para que los usuarios no las cierren
        tabExcursiones.setClosable(false);
        tabSocios.setClosable(false);
        tabInscripciones.setClosable(false);

        //La escena principal la definimos con estos pixeles en concreto
        Scene scene = new Scene(tabPane, 800, 600);

        //Configuramos el escenario principal con el título de la empresa
        primaryStage.setTitle("¡Bienvenidos a Senderos y Montañas! - Menú Principal:");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        //Este es el punto de entrada, el metodo main() llama a launch(args) que iniciliza JavaFX y muestra la ventana.
    }
}
