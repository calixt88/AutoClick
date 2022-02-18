package com.example.autoclicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class HelpController {

        Controller controller;
        Stage helpStage;

        @FXML
        public void ok(ActionEvent event){
            try{
                System.out.println("Closing Help Window");
                helpStage.hide();
            }catch(NullPointerException e){
                System.out.println("Null Pointer Exception");
            }
        }

        @FXML
        public void github(ActionEvent event) throws IOException, URISyntaxException {
            Desktop.getDesktop().browse(new URL("https://github.com/calixt88/xClicker").toURI());
        }

        public void setController(Controller controller){
            this.controller = controller;
        }

        public void setHelpStage(Stage helpStage) {
            this.helpStage = helpStage;
        }
}
