package com.xxxmkxxx.BicycleWorkshop.gui.controllers;

import com.xxxmkxxx.BicycleWorkshop.sql.sqlLite.SQLlite;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    @FXML
    private Button entrace;

    public void checkAccaunt() throws SQLException {
        String logInfo = new SQLlite("workers.db").auntificationAccaunt(loginField.getText(), passwordField.getText()).getLogInfo();
        errorChecker.setText(logInfo);

        if(logInfo.equals("You login successful!")) {
            URL url = getClass().getResource("/mainPage.fxml");
            FXMLLoader loader = new FXMLLoader(url);

            Stage stage = new Stage();
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            stage.setTitle("Bicycle Workshop");
            stage.setScene(new Scene(root));
            stage.show();

            Stage lastStage = (Stage) entrace.getScene().getWindow();
            lastStage.close();
        }
    }
}
