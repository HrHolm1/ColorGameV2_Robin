module com.example.colorgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.colorgame to javafx.fxml;
    exports com.example.colorgame;
}