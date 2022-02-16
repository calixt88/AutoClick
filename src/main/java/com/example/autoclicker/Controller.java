package com.example.autoclicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Calendar;

/*
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

    boolean status;

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
            String hour = hours.getText();
            String minute = minutes.getText();
            String second = seconds.getText();
            String millisecond = milliseconds.getText();

            double hourToSeconds = Integer.parseInt(hour) * 3600;
            double minuteToSeconds = Integer.parseInt(minute) * 60;
            double millisecondToSeconds = Integer.parseInt(millisecond) / 0.001;
            double secondsToSeconds = Integer.parseInt(second);

            double time = hourToSeconds  + millisecondToSeconds + minuteToSeconds + secondsToSeconds;
            long num = Double.valueOf(time).longValue();

            Robot r = new Robot();

            if(repeat.isDisabled()){
                while(status != false){
                    click();
                    Thread.sleep(num);                //idk whats going on
                }
            }else{
                String t = repeatOnly.getText();
                int counter = Integer.parseInt(t);
                for (int i = 0; i < counter; i++){
                    click();
                    Thread.sleep(num);
                }
            }
        }catch(Exception e){
            System.out.println("General Exception Occurred");
        }
    }

    @FXML
    public void custom(ActionEvent event){
        if(customLocation.isSelected()){
            x.setDisable(false);
            y.setDisable(false);

        }else{
            x.setDisable(true);
            y.setDisable(true);
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

}