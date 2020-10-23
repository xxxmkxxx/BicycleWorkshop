package com.xxxmkxxx.BicycleWorkshop;

import java.io.IOException;
import java.util.List;

import com.xxxmkxxx.BicycleWorkshop.controllers.FirstCntroller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BicycleWorkshop extends Application {
    Order order;
    List <Order> listOrders;

    public static void main(String []args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        IWorkingVithData objectSaveData = (IWorkingVithData) SafeData.chooseVariantsSafeData();

        order = new Order(objectSaveData);
        listOrders = order.readOrders();
    }

    @Override
    public void start(Stage stage) {
        Text text = new Text(100, 100, Integer.toString(listOrders.size()));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml\\main.fxml"));
        FirstCntroller controller = loader.getController();

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);

        stage.setTitle("Bicycle Workshop");
        stage.setScene(scene);
        stage.show();

    }
}
