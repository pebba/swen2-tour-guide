package technikum.bohrffer.swen2tourguide;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import technikum.bohrffer.swen2tourguide.models.Tour;

import java.io.IOException;

public class TourApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TourApp.class.getResource("tour-view.fxml")); //tour-view.fxml
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Tour Planner");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}