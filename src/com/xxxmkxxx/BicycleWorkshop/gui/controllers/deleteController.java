package com.xxxmkxxx.BicycleWorkshop.gui.controllers;

import com.xxxmkxxx.BicycleWorkshop.Order;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class deleteController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        group = new ToggleGroup();

        radioButtonDeleteFromNumberOwner.setToggleGroup(group);
        radioButtonDeleteFromNumberBicycle.setToggleGroup(group);
        radioButtonDeleteFromNumberBicycle.setVisible(false);
        radioButtonDeleteFromNumberOwner.setVisible(false);

        checkSetRadioButton();
    }

    @FXML
    private RadioButton radioButtonDeleteFromNumberOwner;

    @FXML
    private RadioButton radioButtonDeleteFromNumberBicycle;

    @FXML
    private TextField fieldNumber;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonCanceling;

    ToggleGroup group;
    private List<Order> listOrders =  MainController.listOrders;
    final boolean[] indicator = {false};
    private boolean tempbool = true;

    private void checkSetRadioButton() {
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (group.getSelectedToggle() != null) {
                    RadioButton button = (RadioButton) group.getSelectedToggle();

                    if(button.getText().equals("Удалить по номеру владельца")) {
                        if(!isCorrectTextField(fieldNumber.getText())) {
                            indicator[0] = true;
                        }
                    }

                    /*if(button.getText().equals("Удалить по номеру велосипеда")) {
                        if(!isCorrectTextField(fieldNumber.getText())) {
                            indicator[0] = true;
                        }
                    }*/
                }
            }
        });
    }

    public void deleteRecord() {
        if(tempbool) {
            new Order(MainController.tempObject).removeOrderForId(Integer.parseInt(fieldNumber.getText()), listOrders);
            MainController.tempListOrders = listOrders;

            Stage lastStage = (Stage) buttonDelete.getScene().getWindow();
            lastStage.close();
        }
        else {

        }
    }

//Проверка коректности написанного в поле текста
    private boolean isCorrectTextField(String textField) {
        if(textField == null || textField.equals("") || textField.equals(" ")) {
            return false;
        }
        else {
            return true;
        }
    }

    public void canceling() {
        Stage inputStage = (Stage) buttonCanceling.getScene().getWindow();
        inputStage.close();
    }

}
