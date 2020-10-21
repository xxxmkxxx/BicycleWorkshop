package com.xxxmkxxx.BicycleWorkshop;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface IWorkingVithData {
    //Метод для аписи новых заказов в файл
    void writeData(List listOrders);

    void safeData();

    List readData();

    void deleteData(int arrId[]);

    void replaceData(int arrId[], List content);

    void writeOrder(Order order);

    void removeOrder(int id);
}
