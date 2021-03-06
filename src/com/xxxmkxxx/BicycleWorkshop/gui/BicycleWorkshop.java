package com.xxxmkxxx.BicycleWorkshop.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import com.xxxmkxxx.BicycleWorkshop.gui.controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BicycleWorkshop extends Application {
    public static void main(String []args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        URL url = getClass().getResource("/loginPage.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        LoginController controller = loader.getController();

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);

        stage.setTitle("Веломастерская");
        stage.setScene(scene);
        stage.show();
    }
}
