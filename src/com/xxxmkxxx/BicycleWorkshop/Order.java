package com.xxxmkxxx.BicycleWorkshop;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Order implements Cloneable{
    private int id;
    private String nameFile = "id.txt";
    private String path = "out/BicycleWorkshop/com/xxxmkxxx/BicycleWorkshop/xml/";

    private IWorkingVithData object;

    private Owner owner;
    private Bicycle bicycle;
    private Repair repair;

//Геттеры для получения объектов
    public Owner getOwner() {
        return owner;
    }

    public Bicycle getBicycle() {
        return bicycle;
    }

    public Repair getRepair() {
        return repair;
    }

//Метод для определения id
    private int incrementId() {
        int id = setId();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + nameFile))) {
            bufferedWriter.write(String.valueOf(++id));
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return id;
    }

//Геттер для получения id
    public int getId(){
        return id;
    }

//Метод для задания значения id
    private int setId() {
        int id = 0;

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path + nameFile))) {
            id =  Integer.parseInt(bufferedReader.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return id;
    }

//Метод для получения заказа по id
    public Order getOrderForId(int id, List listOrders) {
        for(Order order : (List <Order>) listOrders) {
            if(order.getOwner().getId() == id){
                return order;
            }
        }
        return null;
    }

//Метод для добавления элемента
    public void addOrder(Order order, List listOrders) {
        object.writeOrder(order);
        listOrders.add(order);
    }

//Метод для удаления элемента
    public void removeOrderForId(int id, List listOrders) {
        object.removeOrder(id);

        for(int i = 0; i < listOrders.size(); i++) {
            List <Order> tempListOrders = listOrders;
            if(tempListOrders.get(i).getOwner().getId() == id){
                listOrders.remove(i);
            }
        }
    }

    public List <Order> readOrders() {
        return (List <Order>) object.readData();
    }

    public void writeOrders(List listOrders, List newOrders) {
        object.writeData(newOrders);
        listOrders.addAll(newOrders);
    }

    public void replaceOrders(int ids[], List listOrders, List listNewOrders) {
        object.replaceData(ids, listNewOrders);

        List <Order> tempList = new ArrayList(listOrders);
        List <Order> tempNewList = new ArrayList(listNewOrders);

        for(int i = 0; i < listOrders.size(); i++) {
            newIteration: for(int j = 0; j < ids.length; j++) {
                if(tempList.get(i).getOwner().getId() == ids[j]) {
                    for (int k = 0; k < listNewOrders.size(); k++) {
                        if(tempNewList.get(k).getOwner().getId() == ids[j]) {
                            listOrders.set(i, listNewOrders.get(k));
                            break newIteration;
                        }
                    }
                }
            }
        }
    }

    public Order(Object object, String name, String surname, String patronymic, String numberBicycle, String numberPhone, String ... breakage) {
        this.object = (IWorkingVithData) object;
        id = incrementId();
        owner = new Owner(name, surname, patronymic, numberBicycle, numberPhone, id);
        bicycle = new Bicycle(owner, breakage);
        repair = new Repair(owner, bicycle);
    }

    public Order(Object object, int id, String name, String surname, String patronymic, String numberBicycle, String numberPhone, int state, int cost, String date, String ... breakage) {
        this.object = (IWorkingVithData) object;
        this.id = id;
        owner = new Owner(name, surname, patronymic, numberBicycle, numberPhone, this.id, date);
        bicycle = new Bicycle(state, owner, breakage);
        repair = new Repair(cost, owner, bicycle);
    }

    public Order(Object object) {
        this.object = (IWorkingVithData) object;
    }
}
