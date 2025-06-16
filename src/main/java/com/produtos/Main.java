package com.produtos;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/produtos/hello-view.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Estoque de Produtos");
        stage.setScene(scene);
        stage.show();
    ///Iniciador da aplicação///
    }

    public static void main(String[] args) {
        launch(args);
    }
}
