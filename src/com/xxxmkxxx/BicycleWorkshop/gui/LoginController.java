package com.xxxmkxxx.BicycleWorkshop.gui;

import com.xxxmkxxx.BicycleWorkshop.sql.sqlLite.SQLlite;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
