package com.example.autoclicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicBoolean;

public class Controller {

    @FXML
    TextField hours, minutes, seconds, milliseconds, repeat, x, y;

    @FXML
    CheckBox repeatOnly, customLocation;

    @FXML
    Button getButton, stop, startBtn;

    @FXML
    MenuButton buttonChoice;

    Stage helpStage;
    Stage mainStage;

    final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
    Thread thread;
    int xCoordinates = 0;
    int yCoordinates = 0;
    int button = InputEvent.BUTTON1_DOWN_MASK;
    Robot r = new Robot();

    public Controller() throws AWTException {
    }

    void click() {
        try {
            System.out.println("Click");
            r.mousePress(button);
            Thread.sleep(1);
            r.mouseRelease(button);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @FXML
    public void start(ActionEvent event) {
        stop.setDisable(false);
        go();
    }

    public void go() {
        Calendar c = Calendar.getInstance();
        System.out.format("%tl:%tM %tp%n", c, c, c);

        try {
            Robot r = new Robot();
            String hour = hours.getText();
            String minute = minutes.getText();
            String second = seconds.getText();
            String millisecond = milliseconds.getText();



            double hourToSeconds = Double.parseDouble(hour) * 3600;
            double minuteToSeconds = Double.parseDouble(minute) * 60;
            double millisecondToSeconds = Double.parseDouble(millisecond) / 0.001;
            double secondsToSeconds = Double.parseDouble(second);


            double time = (hourToSeconds + millisecondToSeconds + minuteToSeconds + secondsToSeconds) * 1000;
            long num = Double.valueOf(time).longValue();

            if (num < 500) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Interval Issue");
                alert.setContentText("Please enter an interval over 500 milliseconds.");
                alert.showAndWait();
            }

            get();

            if (customLocation.isSelected()) {
                r.mouseMove(xCoordinates, yCoordinates);
            }

            atomicBoolean.set(true);
            Runnable task = () -> conductClick(num);
            thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();
        } catch (NumberFormatException e) {
            System.out.println("Number format Exception has occurred. " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid Input. Please enter a valid integer.");
            alert.setTitle("Number Format Exception");
            alert.showAndWait();
        } catch (AWTException e) {
            System.out.println("An AWT Exception has Occurred. MESSAGE: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Robot Error");
            alert.setTitle("AWT Exception");
            alert.showAndWait();
        } catch (Exception e) {
            System.out.println("A general exception has occurred MESSAGE: " + e.getMessage());
        }
    }

    private void conductClick(long num) {
        try {
            if (repeat.isDisabled()) {
                while (atomicBoolean.get()) {
                    click();
                    Thread.sleep(num);
                }
            }
            if (!repeat.isDisabled()) {
                String t = repeat.getText();
                int counter = Integer.parseInt(t);
                for (int i = 0; i < counter; i++) {
                    click();
                    Thread.sleep(num);
                    if (!atomicBoolean.get()) break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void custom(ActionEvent event) {
        if (customLocation.isSelected()) {
            x.setDisable(false);
            y.setDisable(false);
            getButton.setDisable(false);
        } else {
            x.setDisable(true);
            y.setDisable(true);
            getButton.setDisable(true);
        }
    }

    public void get() {
        try {
            String xCoordinate = x.getText();
            String yCoordinate = y.getText();
            xCoordinates = Integer.parseInt(xCoordinate);
            yCoordinates = Integer.parseInt(yCoordinate);
        } catch (NumberFormatException e) {
            System.out.println("Number Format Exception has Occurred" + e.getMessage());
        }
    }

    @FXML
    public void stopIt(ActionEvent event) {
        stopMethod();
    }

    public void stopMethod(){
        stop.setDisable(true);
        atomicBoolean.set(false);
        thread = null;
    }

    @FXML
    public void RepeatOnly(ActionEvent event) {
        repeat.setDisable(!repeatOnly.isSelected());
    }

    @FXML
    public void help(ActionEvent event) {
        try {
            if (helpStage.isShowing()) {
                helpStage.hide();
            } else {
                System.out.println("Loading Help Screen");
                helpStage.setX(mainStage.getX());
                helpStage.setY(mainStage.getY());
                helpStage.show();
            }
        } catch (NullPointerException e) {
            System.out.println("Null Pointer");
        } catch (Exception e) {
            System.out.println("ope");
        }
    }

    @FXML
    public void right(ActionEvent event) {
        buttonChoice.setText("Right");
        button = InputEvent.BUTTON3_DOWN_MASK;
    }

    @FXML
    public void left(ActionEvent event) {
        buttonChoice.setText("Left");
    }

    public void setStage(Stage stage) {
        helpStage = stage;
    }

    public void setMainStage(Stage stage) {
        mainStage = stage;
    }


}