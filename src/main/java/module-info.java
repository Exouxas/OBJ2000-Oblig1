module com.example.oblig1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oblig1 to javafx.fxml;
    exports com.example.oblig1;
    exports com.example.oblig1.controls;
    opens com.example.oblig1.controls to javafx.fxml;
}