package com.xxxmkxxx.BicycleWorkshop.gui.controllers;

import com.xxxmkxxx.BicycleWorkshop.Order;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class replaceController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private TextField fieldSurName;

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldPatronimyc;

    @FXML
    private TextField fieldNumberPhone;

    @FXML
    private TextField fieldNumberBicycle;

    @FXML
    private TextField fieldState;

    @FXML
    private TextField fieldBreakages;

    @FXML
    private TextField fieldCost;

    @FXML
    private TextField fieldId;

    @FXML
    private Button buttonCanceling;

    @FXML
    private Button buttonRelace;

    List<Order> listOrders;
    Order order;

    public void setValueFields() {
        order = new Order(MainController.tempObject);
        listOrders = MainController.tempListOrders;

        if(isCorrectTextField(fieldId.getText())) {
            order = order.getOrderForId(Integer.parseInt(fieldId.getText()), listOrders);

            fieldSurName.setText(order.getOwner().getSurname());
            fieldName.setText(order.getOwner().getName());
            fieldPatronimyc.setText(order.getOwner().getPatronymic());
            fieldNumberPhone.setText(order.getOwner().getNumberPhone());
            fieldNumberBicycle.setText(order.getOwner().getNumberBicycle());
            fieldState.setText(String.valueOf(order.getBicycle().getState()));

            String tempStr = "";
            for(int i = 0; i < order.getBicycle().getBreakage().length; i++) {
                tempStr += order.getBicycle().getBreakage()[i] + " ";
            }

            fieldBreakages.setText(tempStr);
            fieldCost.setText(String.valueOf(order.getRepair().getCost()));
        }
    }

    public void replaceValues() {
        if(isCorrectTextField(fieldSurName.getText())) order.getOwner().setSurname(fieldSurName.getText());
        if(isCorrectTextField(fieldName.getText())) order.getOwner().setName(fieldName.getText());
        if(isCorrectTextField(fieldPatronimyc.getText())) order.getOwner().setPatronymic(fieldPatronimyc.getText());
        if(isCorrectTextField(fieldNumberPhone.getText())) order.getOwner().setNumberPhone(fieldNumberPhone.getText());
        if(isCorrectTextField(fieldNumberBicycle.getText())) order.getOwner().setNumberBicycle(fieldNumberBicycle.getText());
        if(isCorrectTextField(fieldState.getText())) order.getBicycle().setState(Integer.parseInt(fieldState.getText()));
        if(isCorrectTextField(fieldBreakages.getText())) order.getBicycle().setBreakage(fieldBreakages.getText().split(" "));
        if(isCorrectTextField(fieldCost.getText())) order.getRepair().setCost(Integer.parseInt(fieldCost.getText()));

        MainController.listOrders = listOrders;

        Stage lastStage = (Stage) buttonRelace.getScene().getWindow();
        lastStage.close();
    }

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
