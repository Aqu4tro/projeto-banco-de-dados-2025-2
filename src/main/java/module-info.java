module com.donations {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.donations.model to javafx.base;
    opens com.donations to javafx.graphics;

    exports com.donations;
}
