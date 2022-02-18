package com.example.autoclicker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;

public class AutoClicker extends Application {
    @Override
    public void start(Stage stage) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("design.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("xClicker");
            stage.getIcons().add(new Image("file:Images/xclickerIcon.jpg"));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            FXMLLoader helpLoader = new FXMLLoader(getClass().getResource("help.fxml"));
            Stage helpStage = new Stage();
            Scene helpScene = new Scene(helpLoader.load());
            helpStage.setTitle("Help");
            helpStage.getIcons().add(new Image("file:Images/xclickerIcon.jpg"));
            helpStage.setScene(helpScene);
            helpStage.setResizable(false);
            helpStage.hide();


            Controller controller = fxmlLoader.getController();
            controller.setMainStage(stage);
            controller.setStage(helpStage);

            HelpController helpController = helpLoader.getController();
            helpController.setController(controller);
            helpController.setHelpStage(helpStage);

        }catch(NullPointerException e){
            System.out.println("Null Pointer Exception");
        }catch(IOException e){
            System.out.println("IO Exception from Autoclicker.java");
        }catch(Exception e){
            System.out.println("Ope, none of those");
        }


    }

    public static void main(String[] args) {
        launch();
    }
}