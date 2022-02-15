module com.example.autoclicker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.autoclicker to javafx.fxml;
    exports com.example.autoclicker;
}