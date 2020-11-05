package com.xxxmkxxx.BicycleWorkshop.gui.controllers;

import com.xxxmkxxx.BicycleWorkshop.IWorkingVithData;
import com.xxxmkxxx.BicycleWorkshop.Order;
import com.xxxmkxxx.BicycleWorkshop.gui.tableviews.Bicycles;
import com.xxxmkxxx.BicycleWorkshop.gui.tableviews.Orders;
import com.xxxmkxxx.BicycleWorkshop.gui.tableviews.Owners;
import com.xxxmkxxx.BicycleWorkshop.gui.tableviews.Repairs;
import com.xxxmkxxx.BicycleWorkshop.sql.sqlLite.SQLlite;
import com.xxxmkxxx.BicycleWorkshop.xml.XML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private Menu menuBar;

    @FXML
    private MenuButton chooseAction;

    @FXML
    private MenuItem inputButton;

    @FXML
    private TableView <Owners> tableOwners;

    @FXML
    private TableColumn <Owners, String> ownerId;

    @FXML
    private TableColumn <Owners, String> ownerSurName;

    @FXML
    private TableColumn <Owners, String> ownerName;

    @FXML
    private TableColumn <Owners, String> ownerPatronimyc;

    @FXML
    private TableColumn <Owners, String> ownerNumberPhone;

    @FXML
    private TableView <Bicycles> tableBicycles;

    @FXML
    private TableColumn <Bicycles, String> bicycleNumberBicycle;

    @FXML
    private TableColumn <Bicycles, String> bicycleNumberOwner;

    @FXML
    private TableColumn <Bicycles, String> bicycleState;

    @FXML
    private TableColumn <Bicycles, String> bicycleBrekages;

    @FXML
    private TableView <Repairs> tableRepairs;

    @FXML
    private TableColumn <Repairs, String> repairCost;

    @FXML
    private TableColumn <Repairs, String> repairTerm;

    @FXML
    private TableColumn <Repairs, String> repairNumberBicycle;

    @FXML
    private TableView <Orders> tableOrders;

    @FXML
    private TableColumn <Orders, String> orderId;

    @FXML
    private TableColumn <Orders, String> orderDate;

    @FXML
    private TableColumn <Orders, String> orderNumberBicycle;

    @FXML
    private TableColumn <Orders, String> orderNumberOwner;


    public static IWorkingVithData object;
    public static IWorkingVithData tempObject;
    public static List <Order> listOrders;
    public static List <Order> tempListOrders;
    public static String pathMainDB;
    public static String pathSaveDB;
    public Order tempOrder;
    private String typeDB;

//Метод открытия файла (Открыть)
    public void openFile() {
        FileChooser fileChooser = new FileChooser();

        Stage stage = new Stage();
        Scene scene = new Scene(new Group());

        stage.setTitle("Выберите файл...");
        stage.setScene(scene);

        File file = fileChooser.showOpenDialog(stage);
        pathMainDB = file.getAbsolutePath();

        switch(file.getName().split("\\.")[file.getName().split("\\.").length - 1]) {
            case "db":
                object = new SQLlite(pathMainDB);
                tempObject = new SQLlite(pathMainDB);
                listOrders = object.readData();
                tempListOrders = listOrders;
                typeDB = "db";
                break;
            case "xml":
                object = new XML(pathMainDB);
                tempObject = new XML(pathMainDB);
                listOrders = object.readData();
                tempListOrders = listOrders;
                typeDB = "xml";
                break;
        }
        showData(listOrders);
    }

//Метод для сохранения данных в этот же фаил
    public void safeData() {
        listOrders = tempListOrders;
        object.writeData(listOrders);
    }

//Метод для сохранения данных в выбранный фаил
    public void safeAs() {
        listOrders = tempListOrders;
        FileChooser fileChooser = new FileChooser();

        Stage stage = new Stage();
        Scene scene = new Scene(new Group());

        stage.setTitle("Выберите файл...");
        stage.setScene(scene);

        File file = fileChooser.showSaveDialog(stage);
        fileChooser.setInitialFileName("list");

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("database files (*.db)", "*.xml");
        fileChooser.getExtensionFilters().add(filter);

        pathSaveDB = file.getAbsolutePath();


        switch(typeDB) {
            case "db":
                SQLlite sqLlite = new SQLlite(pathSaveDB);
                sqLlite.safeData();
                sqLlite.writeData(listOrders);
                sqLlite.removeOrder(1);
                break;
            case "xml":
                new XML(pathSaveDB).writeData(listOrders);
                break;
        }
    }

    //Метод для отображения данных таблицы
    public void showData(List <Order> listOrders) {
//Таблица владельцев
        ownerId.setCellValueFactory(new PropertyValueFactory("id"));
        ownerSurName.setCellValueFactory(new PropertyValueFactory("surName"));
        ownerName.setCellValueFactory(new PropertyValueFactory("name"));
        ownerPatronimyc.setCellValueFactory(new PropertyValueFactory("patronimyc"));
        ownerNumberPhone.setCellValueFactory(new PropertyValueFactory("numberPhone"));

        tableOwners.getItems().setAll(getOwnersList(listOrders));

//Таблица велосипедов
        bicycleNumberBicycle.setCellValueFactory(new PropertyValueFactory("numberBicycle"));
        bicycleNumberOwner.setCellValueFactory(new PropertyValueFactory("numberOwner"));
        bicycleState.setCellValueFactory(new PropertyValueFactory("state"));
        bicycleBrekages.setCellValueFactory(new PropertyValueFactory("breakages"));

        tableBicycles.getItems().setAll(getBicyclesList(listOrders));

//Таблица Ремонта
        repairCost.setCellValueFactory(new PropertyValueFactory("cost"));
        repairTerm.setCellValueFactory(new PropertyValueFactory("term"));
        repairNumberBicycle.setCellValueFactory(new PropertyValueFactory("numberBicycle"));

        tableRepairs.getItems().setAll(getRepairsList(listOrders));

//Таблица заказов
        orderId.setCellValueFactory(new PropertyValueFactory("id"));
        orderDate.setCellValueFactory(new PropertyValueFactory("date"));
        orderNumberBicycle.setCellValueFactory(new PropertyValueFactory("numberBicycle"));
        orderNumberOwner.setCellValueFactory(new PropertyValueFactory("numberOwner"));

        tableOrders.getItems().setAll(getOrdersList(listOrders));
    }

//Метод для создания списка владельцев для отображения в таблице
    private List <Owners> getOwnersList(List <Order> listOrders) {
        List <Owners> listOwners = new ArrayList();

        for(int i = 0; i < listOrders.size(); i++) {
            Owners owner = new Owners(
                    String.valueOf(listOrders.get(i).getOwner().getId()),
                    listOrders.get(i).getOwner().getSurname(),
                    listOrders.get(i).getOwner().getName(),
                    listOrders.get(i).getOwner().getPatronymic(),
                    listOrders.get(i).getOwner().getNumberPhone()
            );

            listOwners.add(owner);
        }

        return listOwners;
    }

//Метод для создания списка велосипедов для отображения в таблице
    private List <Bicycles> getBicyclesList(List <Order> listOrders) {
        List <Bicycles> listBicycles = new ArrayList();

        for(int i = 0; i < listOrders.size(); i++) {
            String breakages = "";
            for(int j = 0; j < listOrders.get(i).getBicycle().getBreakage().length; j++) {
                breakages += listOrders.get(i).getBicycle().getBreakage()[j] + " ";
            }

            Bicycles bicycle = new Bicycles(
                    listOrders.get(i).getOwner().getNumberBicycle(),
                    String.valueOf(listOrders.get(i).getOwner().getId()),
                    listOrders.get(i).getBicycle().getState() + "%",
                    breakages
            );

            listBicycles.add(bicycle);
        }

        return listBicycles;
    }

//Метод для создания списка Ремонта для отображения в таблице
    private List <Repairs> getRepairsList(List <Order> listOrders) {
        List <Repairs> listRepairs = new ArrayList();

        for(int i = 0; i < listOrders.size(); i++) {
            Repairs repair = new Repairs(
                   listOrders.get(i).getRepair().getCost() + "р",
                   listOrders.get(i).getRepair().getDateToFinalRepair(),
                   listOrders.get(i).getOwner().getNumberBicycle()
            );

            listRepairs.add(repair);
        }

        return listRepairs;
    }

//Метод для создания списка Заказов для отображения в таблице
    private List <Orders> getOrdersList(List <Order> listOrders) {
        List <Orders> listOrder = new ArrayList();

        for(int i = 0; i < listOrders.size(); i++) {
            Orders order = new Orders(
                    String.valueOf(listOrders.get(i).getOwner().getId()),
                    listOrders.get(i).getOwner().getDateRegistration(),
                    listOrders.get(i).getOwner().getNumberBicycle(),
                    String.valueOf(listOrders.get(i).getOwner().getId())
            );

            listOrder.add(order);
        }

        return listOrder;
    }

//Метод для вставки записи
    public void inputRecord() {
        URL url = getClass().getResource("/inputPage.fxml");
        FXMLLoader loader = new FXMLLoader(url);

        Stage stage = new Stage();
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        stage.setTitle("Добавление записи");
        stage.setScene(new Scene(root));
        stage.show();
    }

//Метод для удаления записи
    public void deleteRecord() {
        URL url = getClass().getResource("/deletePage.fxml");
        FXMLLoader loader = new FXMLLoader(url);

        Stage stage = new Stage();
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("Удаление записи");
        stage.setScene(new Scene(root));
        stage.show();
    }

//Метод для обновления записей
    public void updateRecords() {
        showData(tempListOrders);
    }

    public void replaceRecord() {
        URL url = getClass().getResource("/replacePage.fxml");
        FXMLLoader loader = new FXMLLoader(url);

        Stage stage = new Stage();
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("Изменение записи");
        stage.setScene(new Scene(root));
        stage.show();
    }

}
