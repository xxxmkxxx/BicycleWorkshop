package com.xxxmkxxx.BicycleWorkshop.sql.sqlLite;

import com.xxxmkxxx.BicycleWorkshop.IWorkingVithData;
import com.xxxmkxxx.BicycleWorkshop.Logs.Log;
import com.xxxmkxxx.BicycleWorkshop.Logs.Logger;
import com.xxxmkxxx.BicycleWorkshop.Order;
import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SQLlite implements IWorkingVithData {
    private Logger logger = new Logger();

    private Connection connection;
    private String path = ""; // = "src/com/xxxmkxxx/BicycleWorkshop/sql/sqlLite/";
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
        connectSQL(nameFile);

        String sql = "CREATE TABLE IF NOT EXISTS customers (\n" +
                "\tid INTEGER,\n" +
                "\tname CHAR(15),\n" +
                "\tsurname CHAR(20),\n" +
                "\tpatronymic CHAR(20),\n" +
                "\tnumberbicycle CHAR(10),\n" +
                "\tnumberphone CHAR(11),\n" +
                "\tstate CHAR(3),\n" +
                "\tcost CHAR(8),\n" +
                "\tdate DATA,\n" +
                "\tbreakage TEXT,\n" +
                "\tPRIMARY KEY(id AUTOINCREMENT)\n" +
                ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);

            sql = "INSERT INTO customers (name, surname, patronymic, numberbicycle, numberphone, state, cost, date, breakage) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(sql);

            //preparedStatement.setInt(1, 1);
            preparedStatement.setString(1, "Имя");
            preparedStatement.setString(2, "Фамилия");
            preparedStatement.setString(3, "Отчество");
            preparedStatement.setString(4, "Номер велосипеда");
            preparedStatement.setString(5, "Номер телефона");
            preparedStatement.setString(6, "100%");
            preparedStatement.setString(7, "100р");
            preparedStatement.setString(8, "Дата заказа");
            preparedStatement.setString(9, "Поломки ");

            preparedStatement.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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

            if(resultSet != null) {
                while(resultSet.next()) {
                    content.add (new Order(
                            new SQLlite(nameFile),
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
        lastContent = readData();

        for(int i = 0; i < lastContent.size(); i++) {
           removeOrder(lastContent.get(i).getOwner().getId());
        }

        for(int i = 0; i < tempList.size(); i++) {
            writeOrder(tempList.get(i));
        }

    }

//Метод для записи данных в выбранный файл


    @Override
    public void writeDataAs(List listOrders) {
        List <Order> tempList = new ArrayList(listOrders);
        for(int i = 0; i < tempList.size(); i++) {
            writeOrder(tempList.get(i));
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

//Метод для получения аккаунта по логину
    public ResultSet getAccaunt(String login) throws SQLException {
        connectSQL(nameFile);

        String sql = "SELECT login, password FROM workers WHERE login = ?";
        ResultSet resultSet = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, login);

            resultSet = statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultSet;
    }

//Метод для аунтификация аккаунта
    public Log auntificationAccaunt(String login, String password) throws SQLException {
        ResultSet resultSet = getAccaunt(login);

        if(!resultSet.next()) {
            connection.close();
            return logger.getLogByName("accaunt not found");
        }
        else if(!resultSet.getString(2).equals(password)) {
            connection.close();
            return logger.getLogByName("wrong password");
        }
        else {
            connection.close();
            return logger.getLogByName("login successful");
        }
    }

    public SQLlite(String nameFile) {
        this.nameFile = nameFile;
    }
}

