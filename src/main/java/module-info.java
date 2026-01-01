module com.example.challenge3ecolifesolution {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.challenge3ecolifesolution to javafx.fxml;
    exports com.example.challenge3ecolifesolution;
}