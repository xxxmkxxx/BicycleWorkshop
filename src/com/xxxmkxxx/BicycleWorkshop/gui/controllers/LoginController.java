package com.xxxmkxxx.BicycleWorkshop.gui.controllers;

import com.xxxmkxxx.BicycleWorkshop.sql.sqlLite.SQLlite;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorChecker;

    public void foo() {
        System.out.println("you inuted:" + " " + passwordField.getText());
    }

    public void checkAccaunt() throws SQLException {
        switch(new SQLlite("workers.db").auntificationAccaunt(loginField.getText(), passwordField.getText())) {
            case 0: {
                System.out.println("verification success!");

                URL url = MainController.class.getResource("/mainPage.fxml");

                FXMLLoader loader = new FXMLLoader(url);

                MainController controller = loader.getController();

                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene scene = new Scene(root);

                Stage stage = new Stage();

                stage.setTitle("main");
                stage.setScene(scene);
                stage.show();

                errorChecker.setText("verification success!");
                break;
            }
            case 1: {
                errorChecker.setText("This accaunt not found :(");
                break;
            }
            case 2: {
                errorChecker.setText("You inuted wrong password!");
                break;
            }
        }

    }
}
