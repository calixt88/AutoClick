package com.example.autoclicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Calendar;

/*
TODO: See if the checkboxes can be colored to grey
TODO: See about making the button animation from being clicked
TODO: Be able tp use the drop down menu for left and right click
TODO: Add Alerts in the Exceptions for GUI use
TODO: Add Icon
*/


public class Controller {

    @FXML
    TextField hours, minutes, seconds, milliseconds, repeat, x, y;

    @FXML
    CheckBox repeatOnly, customLocation;

    @FXML
    Button start, getButton, stop;

    Stage helpStage;
    Stage stage;
    boolean status = true;
    int xCoordinates;
    int yCoordinates;

    void click(){
        try {
            System.out.println("Click");
           Robot r = new Robot();
           int button = InputEvent.BUTTON1_DOWN_MASK;
           r.mousePress(button);
           Thread.sleep(400);
           r.mouseRelease(button);
           Thread.sleep(400);

        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void start(ActionEvent event) {

        Calendar c = Calendar.getInstance();
        System.out.format("%tl:%tM %tp%n", c, c, c);

        try{
            Robot r = new Robot();
            String hour = hours.getText();
            String minute = minutes.getText();
            String second = seconds.getText();
            String millisecond = milliseconds.getText();

            double hourToSeconds = Double.parseDouble(hour) * 3600;
            double minuteToSeconds = Double.parseDouble(minute) * 60;
            double millisecondToSeconds = Double.parseDouble(millisecond) / 0.001;
            double secondsToSeconds = Double.parseDouble(second);

            double time = hourToSeconds  + millisecondToSeconds + minuteToSeconds + secondsToSeconds;
            long num = Double.valueOf(time).longValue();

            get();
            r.mouseMove(xCoordinates,yCoordinates);

            if(repeat.isDisabled()){
                while(status != false){
                    click();
                    Thread.sleep(num);
                }
            }else{
                String t = repeat.getText();
                int counter = Integer.parseInt(t);
                for (int i = 0; i < counter; i++){
                    click();
                    Thread.sleep(num);
                }
            }
        }catch(NumberFormatException e){
            System.out.println("Number format Exception has occurred.  MESSAGE:" + e.getMessage());
        }catch(AWTException e){
            System.out.println("An AWT Exception has Occurred. MESSAGE:" + e.getMessage());
        }catch(Exception e){
            System.out.println("A general exception has occurred MESSAGE:" + e.getMessage());
        }
    }

    @FXML
    public void custom(ActionEvent event){
        if(customLocation.isSelected()){
            x.setDisable(false);
            y.setDisable(false);
            getButton.setDisable(false);
        }else{
            x.setDisable(true);
            y.setDisable(true);
            getButton.setDisable(true);
        }
    }

    public void get(){
        try{
            Robot r = new Robot();
            String xCoordinate = x.getText();
            String yCoordinate = y.getText();
            xCoordinates = Integer.parseInt(xCoordinate);
            yCoordinates = Integer.parseInt(yCoordinate);
        }catch(NumberFormatException e){
            System.out.println("Number Format Exception has Occurred" + e.getMessage());
        }catch(AWTException e){
            System.out.println("AWT Exception has Occurred" + e.getMessage());
        }

    }

    @FXML
    public void RepeatOnly(ActionEvent event){
        if(repeatOnly.isSelected()){
            repeat.setDisable(false);
        }else{
            repeat.setDisable(true);
        }
    }

    public void help(){
        if(!helpStage.isShowing()){
            System.out.println("Loading Help Screen");
            helpStage.setX(stage.getX() + stage.getWidth());
            helpStage.setY(stage.getY());
            helpStage.show();
        }
    }

//    public void setController(Controller controller) {
//        this.controller = controller;
//    }

    public void setStage(Stage stage) {
        helpStage = stage;
    }

}