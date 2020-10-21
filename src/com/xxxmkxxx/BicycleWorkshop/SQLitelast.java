package com.xxxmkxxx.BicycleWorkshop;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SQLitelast{
    private Connection co = null;
    private Statement stat;
    private  ResultSet resSet;

    public void connectSQLite(){
        try {{
        try {
            Class.forName("org.sqlite.JDBC");
            this.co = DriverManager.getConnection("jdbc:sqlite:src/SQL/list.db");
            System.out.println("Connected");
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
            Class.forName("org.sqlite.JDBC");
            this.co = DriverManager.getConnection("jdbc:sqlite:src/SQL/list.db");
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void set() throws SQLException{
        String str, sql;

        Scanner in = new Scanner(System.in);

        sql = "INSERT INTO 'customers'('name', 'surname', 'patronymic', 'numberBicycle', 'state', 'breakage', 'cost') VALUES (";

        System.out.print("Введите имя: ");
        sql += "'" + in.nextLine() + "',";

        System.out.print("\nВведите фамилию: ");
        sql += "'" + in.nextLine() + "',";

        System.out.print("\nВведите отчество: ");
        sql += "'" + in.nextLine() + "',";

        System.out.print("\nВведите серийный номер велосипеда: ");
        sql += "'" + in.nextLine() + "',";

        System.out.print("\nВведите состояние вашего велосипеда: ");
        sql += "'" + in.nextLine() + "',";

        System.out.print("\nВведите название поломанной детали: ");
        sql += "'" + in.nextLine() + "',";

        System.out.print("\nВведите стоимость ремонта: ");
        sql += "'" + in.nextLine() + "');";

        this.stat = co.createStatement();
        this.stat.execute(sql);
    }

    public ArrayList <String> get() throws SQLException{
        String str = null, sql;
        ArrayList <String> arrayStr = new ArrayList <String>();
        sql = "SELECT * FROM 'customer';";

        this.stat = co.createStatement();
        this.resSet = this.stat.executeQuery(sql);

        while(this.resSet.next()){
            str = null;

            str += resSet.getString("id") + " ";
            str += resSet.getString("name") + " ";
            str += resSet.getString("surname") + " ";
            str += resSet.getString("patronymic") + " ";
            str += resSet.getString("numberBicycle") + " ";
            str += resSet.getString("state") + " ";
            str += resSet.getString("breakage") + " ";
            str += resSet.getString("cost") + " ";

            arrayStr.add(str);
        }

        return arrayStr;
    }

    public void replace(ArrayList<Owner> owners, ArrayList<Bicycle> bicycles, ArrayList<Repair> repairs) throws SQLException {
        String sql = "";

        for(int i = 0; i < owners.size(); i++) {
//            sql += "UPDATE 'customer' SET ";
//            sql += "'name' = " + "'" + owners.get(i).getName() + "',";
//            sql += "'surname' = " + "'" + owners.get(i).getSurname() + "',";
//            sql += "'patronymic' = " + "'" + owners.get(i).getPatronymic() + "',";
//            sql += "'numberBicycle' = " + "'" + owners.get(i).getNumberBicycle() + "',";
//            sql += "'state' = " + "'" + bicycles.get(i).getState() + "',";
//            sql += "'breakage' = " + "'" + bicycles.get(i).getBreakage() + "',";
//            sql += "'cost' = " + "'" + repairs.get(i).getCost() + "'";
//            sql += " WHERE id = " + i + 1 + ";";

            this.stat = co.createStatement();
            this.stat.execute(sql);
        }
    }
}