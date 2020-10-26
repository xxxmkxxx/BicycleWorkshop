package com.xxxmkxxx.BicycleWorkshop;

import com.xxxmkxxx.BicycleWorkshop.sql.sqlLite.SQLite;
import com.xxxmkxxx.BicycleWorkshop.xml.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
            someMethod();
    }

    public static void someMethod() throws IOException, SAXException, ParserConfigurationException {
        XML xml = new XML("list.xml");
        SQLite sql = new SQLite("list.db");
        List <Order> listOrders = xml.readData();
        //Order.addOrder(new Order("Вася", "Васюнин", "Петрович", "sffa5h45", "+79873613896", "Диск", "Шина"), listOrders, xml);

        //Order.removeOrderForId(3, listOrders, xml);


        /*for(int i = 0; i < 20; i++) {
            Order.removeOrderForId(i, listOrders, xml);
        }*/

        //xml.generateRedableXML();

        //sql.writeOrder(new Order("Вася", "Васюнин", "Петрович", "sffa5h45", "+79873613896", "Диск", "Шина"));
        //sql.removeOrder(5);
        //sql.readData();
    }
}
