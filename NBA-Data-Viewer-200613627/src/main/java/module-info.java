module com.example.nbadataviewer200613627 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.nbadataviewer200613627 to javafx.fxml;
    exports com.example.nbadataviewer200613627;
}