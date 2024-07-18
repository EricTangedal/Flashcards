package edu.miracosta.cs210.cs210finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ApplicationManager.getInstance();
        ApplicationManager.getInstance().setDecks(ApplicationManager.load());
        FXMLLoader loader = new FXMLLoader(ApplicationManager.class.getResource("Home.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Totally Legit Flashcards");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            event.consume();
            ApplicationManager.showSaveConfirmationDialog(stage);
        });
        ApplicationManager.getInstance().setStage(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
