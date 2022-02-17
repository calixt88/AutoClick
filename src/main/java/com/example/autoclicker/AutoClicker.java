package com.example.autoclicker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class AutoClicker extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("design.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("AutoClicker");
        stage.setScene(scene);
        Image icon = new Image("file:clicker.jpg");
        stage.getIcons().add(icon);
        stage.show();


        Stage helpStage = new Stage();
        FXMLLoader helpLoader = new FXMLLoader();
        helpLoader.setLocation(getClass().getResource("help.fxml"));
        Parent rootTwo = helpLoader.load();
        helpStage.setTitle("Help Window");
        helpStage.setScene(new Scene(rootTwo));


        Controller controller = helpLoader.getController();
        //controller.setController(controller);
        //controller.setStage();
        helpStage.setOnCloseRequest(event -> controller.help());

    }

    public static void main(String[] args) {
        launch();
    }
}