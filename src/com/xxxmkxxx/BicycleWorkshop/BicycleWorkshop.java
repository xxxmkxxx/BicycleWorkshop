package com.xxxmkxxx.BicycleWorkshop;

import java.util.List;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
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

        Group group = new Group();
        group.getChildren().add(text);

        Scene scene = new Scene(group);

        stage.setWidth(350);
        stage.setHeight(350);
        stage.setTitle("Bicycle Workshop");
        stage.setScene(scene);
        stage.show();

    }
}
