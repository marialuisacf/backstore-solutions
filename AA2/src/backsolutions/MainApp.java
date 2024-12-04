package backsolutions;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainApp extends Application { //Esta clase es el Test de que JavaFX funciona correctamente
    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("¡¡Hemos configurado JavaFX correctamente!!");
        Scene scene = new Scene(label, 300, 200);
        primaryStage.setTitle("Prueba JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
