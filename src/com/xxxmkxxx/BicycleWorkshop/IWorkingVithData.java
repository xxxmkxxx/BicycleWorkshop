package com.xxxmkxxx.BicycleWorkshop;

import com.xxxmkxxx.BicycleWorkshop.Order;

import java.util.List;

public interface IWorkingVithData {
    //Метод для аписи новых заказов в файл
    void writeData(List listOrders);

    void writeDataAs(List listOrders);

    void safeData();

    List readData();

    void deleteData(int arrId[]);

    void replaceData(int arrId[], List content);

    void writeOrder(Order order);

    void removeOrder(int id);
}
