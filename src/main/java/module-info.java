module com.example.pongjess {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pongjess to javafx.fxml;
    exports com.example.pongjess;
}