module com.example.zmeykadesktop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zmeykadesktop to javafx.fxml;
    exports com.example.zmeykadesktop;
}