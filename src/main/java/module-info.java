module technikum.bohrffer.swen2tourguide {
    requires javafx.controls;
    requires javafx.fxml;

    opens technikum.bohrffer.swen2tourguide to javafx.fxml;
    exports technikum.bohrffer.swen2tourguide;
}