module technikum.bohrffer.swen2tourguide {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;

    opens technikum.bohrffer.swen2tourguide to javafx.fxml;
    exports technikum.bohrffer.swen2tourguide;
    exports technikum.bohrffer.swen2tourguide.controllers;
    opens technikum.bohrffer.swen2tourguide.controllers to javafx.fxml;
    opens technikum.bohrffer.swen2tourguide.models to javafx.base;
}
