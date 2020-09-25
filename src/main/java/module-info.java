module Test {
    requires javafx.controls;
    requires javafx.fxml;

    opens world.ucode to javafx.fxml;
    exports world.ucode;
}