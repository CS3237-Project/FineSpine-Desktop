module com.example.finespinedesktop {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.finespinedesktop to javafx.fxml;
    exports com.example.finespinedesktop;
}