package com.xxxmkxxx.BicycleWorkshop.gui.controllers;

import com.xxxmkxxx.BicycleWorkshop.Order;
import com.xxxmkxxx.BicycleWorkshop.xml.XML;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private Label test;

    public void foo() {
        XML xml = new XML("list.xml");
        Order order = new Order(xml);
        List<Order> orderList = new ArrayList();
        orderList = order.readOrders();
        xml.generateRedableXML();

        test.setText(orderList.get(0).getOwner().getName());
    }

}
