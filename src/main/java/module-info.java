module technikum.bohrffer.swen2tourguide {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires jakarta.persistence;

    opens technikum.bohrffer.swen2tourguide to javafx.fxml;
    exports technikum.bohrffer.swen2tourguide;
    exports technikum.bohrffer.swen2tourguide.controllers;
    opens technikum.bohrffer.swen2tourguide.controllers to javafx.fxml;
    opens technikum.bohrffer.swen2tourguide.models to javafx.base;
}
