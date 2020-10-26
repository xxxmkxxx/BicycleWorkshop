package com.xxxmkxxx.BicycleWorkshop.sql.sqlLite;

import com.xxxmkxxx.BicycleWorkshop.IWorkingVithData;
import com.xxxmkxxx.BicycleWorkshop.Order;
import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLite implements IWorkingVithData {
    private Connection connection;
    private String path = "src\\com\\xxxmkxxx\\BicycleWorkshop\\sql\\sqlLite\\";
    private String nameFile;
    private String link = "jdbc:sqlite:";
    private List <Order> lastContent;

    private void connectSQL(String nameFile){
        try {
            DriverManager.registerDriver(new JDBC());
            connection = DriverManager.getConnection(link + path + nameFile);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

//Метод для сохранения данных
    @Override
    public void safeData() {

    }

//Метод для чтения данных
    @Override
    public List readData() {
        connectSQL(nameFile);

        String sql = "SELECT id, name, surname, patronymic, numberbicycle, numberphone, state, cost, date, breakage FROM customers";
        ResultSet resultSet = null;
        List <Order> content = new ArrayList();

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                content.add (new Order(
                        new SQLite(nameFile),
//id
                        resultSet.getInt(1),
//Имя
                        resultSet.getString(2),
//Фамилия
                        resultSet.getString(3),
//Отчество
                        resultSet.getString(4),
//Номер велосипеда
                        resultSet.getString(5),
//Номер телефона
                        resultSet.getString(6),
//Состояние
                        Integer.parseInt(resultSet.getString(7).split("%")[0]),
//Стоимость ремонта
                        Integer.parseInt(resultSet.getString(8).split("р")[0]),
//Дата
                        resultSet.getString(9),
//Поломки
                        resultSet.getString(10).split(" ")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        lastContent = new ArrayList(content);

        return content;
    }

//Метод для записи данных
    @Override
    public void writeData(List list) {
        List <Order> tempList = new ArrayList(list);
        boolean indicator = false;

        for(int i = 0; i < list.size(); i++) {
            nextIdex: if(tempList.get(i).getOwner().getId() != lastContent.get(i).getOwner().getId()) {
                for(int j = lastContent.size(); j > i; j--) {
                    if(lastContent.get(j).getOwner().getId() == tempList.get(i).getOwner().getId()) {
                        continue;
                    }
                    else {
                        indicator = true;
                    }
                }

                if(indicator) {
                    writeOrder((Order) list.get(i));
                    break nextIdex;
                }
            }

            if(indicator) writeOrder((Order) list.get(i));
        }
    }

//Метод для удаления данных
    @Override
    public void deleteData(int arrId[]) {
        for(int i = 0; i < arrId.length; i++) {
           removeOrder(arrId[i]);
        }
    }

//Метод для изменения
    @Override
    public void replaceData(int arrId[], List listOrders) {
        List <Order> tempList = new ArrayList (listOrders);

        for(int i = 0; i < arrId.length; i++) {
            for(int j = 0; j < listOrders.size(); j++) {
                if(tempList.get(j).getOwner().getId() == arrId[i]) {
                    replaceOrder(arrId[i], tempList.get(j));
                }
            }
        }
    }
//Метод для записи заказа
    @Override
    public void writeOrder(Order order) {
        connectSQL(nameFile);

        String sql = "INSERT INTO customers (name, surname, patronymic, numberbicycle, numberphone, state, cost, date, breakage) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, order.getOwner().getName());
            preparedStatement.setString(2, order.getOwner().getSurname());
            preparedStatement.setString(3, order.getOwner().getPatronymic());
            preparedStatement.setString(4, order.getOwner().getNumberBicycle());
            preparedStatement.setString(5, order.getOwner().getNumberPhone());
            preparedStatement.setString(6, order.getBicycle().getState() + "%");
            preparedStatement.setString(7, order.getRepair().getCost() + "р");
            preparedStatement.setString(8, order.getOwner().getDateRegistration());

            String tempStr = "";
            for(int i = 0; i < order.getBicycle().getBreakage().length; i++) {
                tempStr += order.getBicycle().getBreakage()[i] + " ";
            }
            preparedStatement.setString(9, tempStr);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void replaceOrder(int id, Order order) {
        connectSQL(nameFile);

        String sql = "UPDATE customers SET name = ?, surname = ?, patronymic = ?, numberbicycle = ?, numberphone = ?, state = ?, cost = ?, date = ?, breakage = ? WHERE id = ?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(2, order.getOwner().getName());
            preparedStatement.setString(3, order.getOwner().getSurname());
            preparedStatement.setString(4, order.getOwner().getPatronymic());
            preparedStatement.setString(5, order.getOwner().getNumberBicycle());
            preparedStatement.setString(6, order.getOwner().getNumberPhone());
            preparedStatement.setString(7, order.getBicycle().getState() + "%");
            preparedStatement.setString(8, order.getRepair().getCost() + "р");
            preparedStatement.setString(9, order.getOwner().getDateRegistration());

            String tempStr = "";
            for(int i = 0; i < order.getBicycle().getBreakage().length; i++) {
                tempStr += order.getBicycle().getBreakage()[i] + " ";
            }
            preparedStatement.setString(10, tempStr);
            preparedStatement.setInt(11, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

//Метод для удаления записи
    @Override
    public void removeOrder(int id) {
        connectSQL(nameFile);

        String sql = "DELETE FROM customers WHERE id = ?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public SQLite(String nameFile) {
        this.nameFile = nameFile;
    }
}

