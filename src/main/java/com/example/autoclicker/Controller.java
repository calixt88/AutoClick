package com.example.autoclicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/*
TODO: Round corners on all buttons and borders
TODO: Change the user's text in the text fields to white
TODO: See if the checkboxes can be colored to grey
TODO: See about making the button animation from being clicked
*/


public class Controller {

    @FXML
    TextField hours, minutes, seconds, milliseconds, repeat, x, y;

    @FXML
    CheckBox repeatOnly, customLocation;

    @FXML
    Button start;

    @FXML
    public void handle(ActionEvent event){
        System.out.println("Start Button Clicked");
        start.setStyle("-fx-background-color: #000000");
    }

    void getTime(){
        String hour = hours.getText();
        String minute = minutes.getText();
        String second = seconds.getText();
        String millisecond = milliseconds.getText();
    }

}