package com.xxxmkxxx.BicycleWorkshop.gui.controllers;

import com.xxxmkxxx.BicycleWorkshop.IWorkingVithData;
import com.xxxmkxxx.BicycleWorkshop.Order;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.xxxmkxxx.BicycleWorkshop.gui.controllers.MainController.tempListOrders;

public class inputController implements Initializable {
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
    private CheckBox steering;

    @FXML
    private CheckBox rightPedal;

    @FXML
    private CheckBox frame;

    @FXML
    private CheckBox wheel;

    @FXML
    private CheckBox speed;

    @FXML
    private CheckBox chain;

    @FXML
    private CheckBox rearWing;

    @FXML
    private CheckBox leftPedal;

    @FXML
    private CheckBox frontFender;

    @FXML
    private CheckBox frontDisc;

    @FXML
    private CheckBox rearDisc;

    @FXML
    private CheckBox frontStars;

    @FXML
    private CheckBox backStars;

    @FXML
    private CheckBox shockAbsorbers;

    @FXML
    private Button buttonCanceling;

    @FXML
    private Button buttonInput;


    private MainController mainController;
    private List<Order> listOrders;
    private IWorkingVithData object;

//Проверка коректности написанного в поле текста
    private boolean isCorrectTextField(String textField) {
        if(textField == null || textField.equals("") || textField.equals(" ")) {
            return false;
        }
        else {
            return true;
        }
    }

    public void inputRecord() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/inputPage.fxml"));
        mainController = loader.getController();
        object = MainController.tempObject;
        listOrders = tempListOrders;

        boolean correct = true;

        if(!isCorrectTextField(fieldSurName.getText())) correct = false;
        if(!isCorrectTextField(fieldName.getText())) correct = false;
        if(!isCorrectTextField(fieldPatronimyc.getText())) correct = false;
        if(!isCorrectTextField(fieldNumberPhone.getText())) correct = false;
        if(!isCorrectTextField(fieldNumberBicycle.getText())) correct = false;

        if(correct) {
            String breakages = "";

            if(steering.isSelected()) breakages += "Руль ";
            if(rightPedal.isSelected()) breakages += "Правая_педаль ";
            if(frame.isSelected()) breakages += "Рама ";
            if(wheel.isSelected()) breakages += "Колесо ";
            if(speed.isSelected()) breakages += "Скорости ";
            if(chain.isSelected()) breakages += "Цепь ";
            if(rearWing.isSelected()) breakages += "Заднее_крыло ";
            if(leftPedal.isSelected()) breakages += "Левая_педаль ";
            if(frontFender.isSelected()) breakages += "Переднее_крыло ";
            if(frontDisc.isSelected()) breakages += "Передний_диск ";
            if(rearDisc.isSelected()) breakages += "Задний_диск ";
            if(frontStars.isSelected()) breakages += "Передняя(ие)_звёзды ";
            if(backStars.isSelected()) breakages += "Задняя(ие)_звёзды ";
            if(shockAbsorbers.isSelected()) breakages += "Амортизаторы ";

            listOrders.add(
                    new Order(
                            object,
                            fieldName.getText(),
                            fieldSurName.getText(),
                            fieldPatronimyc.getText(),
                            fieldNumberBicycle.getText(),
                            fieldNumberPhone.getText(),
                            breakages.split(" ")
                    )
            );
        }

        MainController.tempListOrders = listOrders;
        MainController.listOrders = listOrders;

        Stage lastStage = (Stage) buttonInput.getScene().getWindow();
        lastStage.close();
    }

    public void canceling() {
        Stage inputStage = (Stage) buttonCanceling.getScene().getWindow();
        inputStage.close();
    }
}
