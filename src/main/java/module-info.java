module com.produtos {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.produtos to javafx.graphics, javafx.fxml;
    exports com.produtos;
    opens com.produtos.controller to javafx.fxml;
    opens com.produtos.dao to javafx.fxml;
    opens com.produtos.model to javafx.fxml;
}