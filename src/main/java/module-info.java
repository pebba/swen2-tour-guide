module technikum.bohrffer.swen2tourguide {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires org.apache.logging.log4j;
    requires kernel;
    requires layout;

    opens technikum.bohrffer.swen2tourguide to javafx.fxml, org.hibernate.orm.core;
    exports technikum.bohrffer.swen2tourguide;
    exports technikum.bohrffer.swen2tourguide.controllers;
    opens technikum.bohrffer.swen2tourguide.controllers to javafx.fxml;
    opens technikum.bohrffer.swen2tourguide.models to javafx.base, org.hibernate.orm.core;
}
