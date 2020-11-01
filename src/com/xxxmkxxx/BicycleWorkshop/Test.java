package com.xxxmkxxx.BicycleWorkshop;

import com.xxxmkxxx.BicycleWorkshop.sql.sqlLite.SQLlite;
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
        SQLlite sql = new SQLlite("list.db");
        List <Order> listOrders = xml.readData();
        //new Order(xml).addOrder(new Order(xml,"Вася", "Васюнин", "Петрович", "sffa5h45", "+79873613896", "Диск", "Шина"), listOrders);

        //new Order(xml).removeOrderForId(16, listOrders);


        /*for(int i = 0; i < 20; i++) {
            Order.removeOrderForId(i, listOrders, xml);
        }*/

        xml.generateRedableXML();

        //sql.writeOrder(new Order("Вася", "Васюнин", "Петрович", "sffa5h45", "+79873613896", "Диск", "Шина"));
        //sql.removeOrder(5);
        //sql.readData();
    }
}
