package com.xxxmkxxx.BicycleWorkshop;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;

public class XMLlast{
    private Document document;
    private NodeList listId;
    private Node root;

    public void connectXML(String nameFile) throws IOException, SAXException, ParserConfigurationException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        this.document = builder.parse("src/XML/" + nameFile);
        this.document.getDocumentElement().normalize();
        this.listId = document.getDocumentElement().getElementsByTagName("id");
    }

    private  String findFieldById(int id, String nameField){
        String str = null;

        for(int i = 0; i < this.listId.getLength(); i++){
            String atribute = this.listId.item(i).getAttributes().item(0).getNodeValue();
            int atr = Integer.parseInt(atribute);

            if(atr == id){
                Node person = this.listId.item(i);
                NodeList personFields = person.getChildNodes();

                for(int j = 0; j < personFields.getLength(); j++){
                    if(personFields.item(j).getNodeName().equals(nameField)){
                        if(personFields.item(j).getNodeType() == Node.ELEMENT_NODE){
                            str += " " + personFields.item(j).getTextContent();
                            str += i + 1;
                        }
                    }
                }
            }
        }
        return str;
    }

    public NodeList getListId(){return listId;}

    public int setId(){
        int id = this.listId.getLength() + 1;
        return id;
    }

    public ArrayList <String> get(){
        String str;
        ArrayList<String> arrayStr = new ArrayList<String>();
        Scanner in = new Scanner(System.in);

        /*System.out.println("Введите \"all\", если хотите вывеести все записи;\n" +
                "Введите номер записи, если хотете вывести запись по идентификационному номеру;\n" +
                "Напишите любой символ русского алфавита, если хотите вывести определённое поле записи с указанным вами номером записи.");
        str = in.nextLine();*/

        str = "all";

        if (str.equals("all")){
            for(int i = 0; i < this.listId.getLength(); i++){
                Node person = this.listId.item(i);
                NodeList personFields = person.getChildNodes();
                String temp = "";

                for(int j = 0; j < personFields.getLength(); j++){
                    if(personFields.item(j).getNodeType() == Node.ELEMENT_NODE){
                        temp += personFields.item(j).getTextContent() + " ";
                    }
                }

                temp += i + 1;
                arrayStr.add(temp);
            }
        }
        else if(str.matches("[-+]?\\d+")){
            int id = Integer.parseInt(str);

            for(int i = 0; i < this.listId.getLength(); i++){
                String atribute = this.listId.item(i).getAttributes().item(0).getNodeValue();
                int atr = Integer.parseInt(atribute);

                if(atr == id){
                    Node person = this.listId.item(i);
                    NodeList personFields = person.getChildNodes();
                    String temp = null;

                    for(int j = 0; j < personFields.getLength(); j++){
                        temp += personFields.item(j).getTextContent() + " ";
                    }

                    temp += i++;
                }
            }
            arrayStr.add(str);
        }
        else{
            System.out.println("Введите id записи...");
            int id = in.nextInt();

            switch(str){
                case("name"):
                {
                    arrayStr.add(this.findFieldById(id, str));
                    break;
                }
                case("surname"):
                {
                    arrayStr.add(this.findFieldById(id, str));
                    break;
                }
                case("patronymic"):
                {
                    arrayStr.add(this.findFieldById(id, str));
                    break;
                }
                case("date"):
                {
                    arrayStr.add(this.findFieldById(id, str));
                    break;
                }
                case("numberBicycle"):
                {
                    arrayStr.add(this.findFieldById(id, str));
                    break;
                }
                case("state"):
                {
                    arrayStr.add(this.findFieldById(id, str));
                    break;
                }
                case("breakage"):
                {
                    arrayStr.add(this.findFieldById(id, str));
                    break;
                }
                case("cost"):
                {
                    arrayStr.add(this.findFieldById(id, str));
                    break;
                }
                case("term"):
                {
                    arrayStr.add(this.findFieldById(id, str));
                    break;
                }
                default:
                {
                    System.out.println("Ошибка! такого поля не существует!");
                }
            }
        }

        return arrayStr;
    }

    public Node getRootField() {
        return this.document;
    }

    public void write() throws FileNotFoundException {

        Node root = document.getDocumentElement();

        Element id = document.createElement("id");
        id.setAttribute("id", Integer.toString(setId()));

        Scanner text = new Scanner(System.in);

        System.out.print("Введите Фамилию: ");
        Element surname = document.createElement("surname");
        surname.setTextContent(text.nextLine());

        System.out.print("Введите Имя: ");
        Element name = document.createElement("name");
        name.setTextContent(text.nextLine());

        System.out.print("Введите Отчество: ");
        Element patronymic = document.createElement("patronymic");
        patronymic.setTextContent(text.nextLine());

        Element date = document.createElement("date");
        Date nowDate = new Date();
        date.setTextContent(nowDate.toString());

        System.out.print("Введите Серийный номер велосипеда: ");
        Element numberBicycle = document.createElement("numberBicycle");
        numberBicycle.setTextContent(text.nextLine());

        System.out.print("Введите Процент состояния велосипеда: ");
        Element state = document.createElement("state");
        state.setTextContent(text.nextLine() + "%");

        System.out.print("Введите Название поломанной детали велосипеда: ");
        Element breakage = document.createElement("breakage");
        breakage.setTextContent(text.nextLine());

        System.out.print("Введите Предполагаемая стоимость ремонта: ");
        Element cost = document.createElement("cost");
        cost.setTextContent(text.nextLine() + "р");

        System.out.print("Введите Предполагаемую дату завершения ремонта: ");
        Element term = document.createElement("term");
        term.setTextContent(text.nextLine());

        id.appendChild(name);
        id.appendChild(surname);
        id.appendChild(patronymic);
        id.appendChild(date);
        id.appendChild(numberBicycle);
        id.appendChild(state);
        id.appendChild(breakage);
        id.appendChild(cost);
        id.appendChild(term);

        root.appendChild(id);

        Transformer tr = null;


        try {
            tr = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }

        DOMSource source = new DOMSource(document);
        FileOutputStream fos = new FileOutputStream("src/XML/list.xml");
        StreamResult result = new StreamResult(fos);
        try {
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void replace(ArrayList<Owner> owners, ArrayList<Bicycle> bicycles, ArrayList<Repair> repairs) throws IOException, ParserConfigurationException, SAXException {

        for(int i = 0; i < owners.size(); i++) {
//            this.listId.item(i).getChildNodes().item(1).setTextContent(owners.get(i).getName());
//            this.listId.item(i).getChildNodes().item(3).setTextContent(owners.get(i).getSurname());
//            this.listId.item(i).getChildNodes().item(5).setTextContent(owners.get(i).getPatronymic());
//            this.listId.item(i).getChildNodes().item(9).setTextContent(owners.get(i).getNumberBicycle());
//            this.listId.item(i).getChildNodes().item(11).setTextContent(Integer.toString(bicycles.get(i).getState()));
//            this.listId.item(i).getChildNodes().item(13).setTextContent(bicycles.get(i).getBreakage());
//            this.listId.item(i).getChildNodes().item(5).setTextContent(Integer.toString(repairs.get(i).getCost()));
        }
        Transformer tr = null;


        try {
            tr = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }

        DOMSource source = new DOMSource(document);
        FileOutputStream fos = new FileOutputStream("src/XML/list.xml");
        StreamResult result = new StreamResult(fos);
        try {
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}