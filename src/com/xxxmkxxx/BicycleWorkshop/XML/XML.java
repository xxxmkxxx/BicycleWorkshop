package com.xxxmkxxx.BicycleWorkshop.XML;

import com.sun.security.jgss.GSSUtil;
import com.xxxmkxxx.BicycleWorkshop.IWorkingVithData;
import com.xxxmkxxx.BicycleWorkshop.Order;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class XML implements IWorkingVithData {
    private DocumentBuilder builder;
    private Document document;
    private NodeList listId;
    private String indentable = "no";
    private String path = "src\\com\\xxxmkxxx\\BicycleWorkshop\\XML\\";
    private String nameFile;


    private List <Order> lastContent;

//Метод для подключения файла
    private void connectXML(String nameFile) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        document = builder.parse(path + nameFile);
    }

//Метод для записи всех заказов в список для дальнейшей работы с заказами
    @Override
    public List readData() {
        try {
            connectXML(nameFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        listId = document.getDocumentElement().getElementsByTagName("id");

        List <Order> content = new ArrayList();
        for(int i = 0; i < listId.getLength(); i++) {
            NodeList listContent = listId.item(i).getChildNodes();

            content.add(
                    new Order(
                            new XML(nameFile),
//id
                            Integer.parseInt(listId.item(i).getAttributes().item(0).getTextContent()),
//Имя
                            listContent.item(0).getTextContent(),
//Фамилия
                            listContent.item(1).getTextContent(),
//Отчество
                            listContent.item(2).getTextContent(),
//Серийный номер велосипеда
                            listContent.item(4).getTextContent(),
//Номер телефона
                            listContent.item(5).getTextContent(),
//Состояние
                            Integer.parseInt(listContent.item(6).getTextContent().split("%")[0]),
//Цена ремонта
                            Integer.parseInt(listContent.item(7).getTextContent().split("р")[0]),
//Дата
                            listContent.item(8).getTextContent(),
//Поломанные детали
                            listContent.item(9).getTextContent().split(" ")
                    )
            );
        }

        lastContent = new ArrayList(content);
        return content;
    }

//Метод для аписи новых заказов в файл
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

//Метод для сохранения всех заказов в файл
    @Override
    public void safeData(){
        Transformer tr = null;

        try {
            tr = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }

        DOMSource source = new DOMSource(document);
        FileOutputStream fos = null;
        try {
            if(indentable == "yes") {
                fos = new FileOutputStream(path + "readable_" + nameFile);
            }
            else {
                fos = new FileOutputStream(path + nameFile);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StreamResult result = new StreamResult(fos);
        try {
            tr.setOutputProperty(OutputKeys.INDENT, indentable);
            tr.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
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

//Метод для записи заказа в файл
    public void writeOrder(Order order){
        Node root = document.getDocumentElement();
//id
        Element id = document.createElement("id");
        id.setAttribute("id", Integer.toString(order.getId()));
//Фамилия
        Element surname = document.createElement("surname");
        surname.setTextContent(order.getOwner().getSurname());
//Имя
        Element name = document.createElement("name");
        name.setTextContent(order.getOwner().getName());
//Отчество
        Element patronymic = document.createElement("patronymic");
        patronymic.setTextContent(order.getOwner().getPatronymic());
//Дата
        Element date = document.createElement("date");
        date.setTextContent(order.getOwner().getDateRegistration());
//Серийный номер велосипеда
        Element numberBicycle = document.createElement("numberBicycle");
        numberBicycle.setTextContent(order.getOwner().getNumberBicycle());
//Номер телефона
        Element numberPhone = document.createElement("numberPhone");
        numberBicycle.setTextContent(order.getOwner().getNumberPhone());
//Процент состояния велосипеда
        Element state = document.createElement("state");
        state.setTextContent(order.getBicycle().getState() + "%");
//Стоимость ремонта
        Element cost = document.createElement("cost");
        cost.setTextContent(order.getRepair().getCost() + "р");
//Дата завершения ремонта
        Element term = document.createElement("term");
        term.setTextContent(order.getRepair().getDateToFinalRepair());
//Поломанные детали
        String breakages = "";
        for(int i = 0; i < order.getBicycle().getBreakage().length; i++) {
            breakages += order.getBicycle().getBreakage()[i] + " ";
        }
        Element breakage = document.createElement("breakage");
        breakage.setTextContent(breakages);

        id.appendChild(name);
        id.appendChild(surname);
        id.appendChild(patronymic);
        id.appendChild(date);
        id.appendChild(numberBicycle);
        id.appendChild(numberPhone);
        id.appendChild(state);
        id.appendChild(cost);
        id.appendChild(term);
        id.appendChild(breakage);

        root.appendChild(id);

        safeData();
    }

//Метод для изменения заказа по id
    public void replaceOrder(int id, Order order){
        id--;
        listId = document.getDocumentElement().getElementsByTagName("id");
//Имя
        listId.item(id).getChildNodes().item(0).setTextContent(order.getOwner().getName());
//Фамилия
        listId.item(id).getChildNodes().item(1).setTextContent(order.getOwner().getSurname());
//Отчество
        listId.item(id).getChildNodes().item(2).setTextContent(order.getOwner().getPatronymic());
//Дата
        listId.item(id).getChildNodes().item(3).setTextContent(order.getOwner().getDateRegistration());
//Серийный номер велосипеда
        listId.item(id).getChildNodes().item(4).setTextContent(order.getOwner().getNumberBicycle());
//Номер телефона
        listId.item(id).getChildNodes().item(5).setTextContent(order.getOwner().getNumberPhone());
//Поломанные детали
        String breakages = "";
        for(int i = 0; i < order.getBicycle().getBreakage().length; i++) {
            breakages += order.getBicycle().getBreakage()[i] + " ";
        }
        listId.item(id).getChildNodes().item(6).setTextContent(breakages);
//Стоимость
        listId.item(id).getChildNodes().item(7).setTextContent(String.valueOf(order.getRepair().getCost()));
//Дата окончания ремонта
        listId.item(id).getChildNodes().item(8).setTextContent(order.getRepair().getDateToFinalRepair());

        safeData();
    }

//Метод для удаления заказа по id
    public void removeOrder(int id) {
        listId = document.getDocumentElement().getElementsByTagName("id");
        boolean in = false;

        for(int i = 0; i < listId.getLength(); i++) {
            if(listId.item(i).getAttributes().getNamedItem("id").getTextContent().equals(Integer.toString(id))) {
                Node removedOrder = listId.item(i).getParentNode();
                removedOrder.removeChild(listId.item(i));

                in = true;

                safeData();
                break;
            }
        }
        if(!in) System.out.println("Элемента: " + id + " не существует!");
    }

//Метод для создание удобночитаемого XML файла
    public void generateRedableXML() {
        indentable = "yes";

        safeData();

        indentable = "no";
    }

    public XML(String nameFile) {
        this.nameFile = nameFile;
    }
}
