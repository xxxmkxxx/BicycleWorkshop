package com.xxxmkxxx.BicycleWorkshop;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Owner {
    private String name = "Noname";
    private String surname = "Default";
    private String patronymic = "Nothing";
    private String numberBicycle;
    private String numberPhone;
    private String dateRegistration;
    private int id;

//Сетеры для установки значений в поля
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setNumberBicycle(String numberBicycle) {
        this.numberBicycle = numberBicycle;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

//Геттеры для получения информации из полей
    public String getNumberBicycle(){
        return numberBicycle;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public String getDateRegistration() {
        return dateRegistration;
    }

    public int getId() {
        return id;
    }

    public Owner(String name, String surname, String patronymic, String numberBicycle, String numberPhone, int id) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.numberBicycle = numberBicycle;
        this.numberPhone = numberPhone;
        this.id = id;

//Получаем дату и время содания заявки
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        dateRegistration = simpleDateFormat.format(date);
    }

    public Owner(String name, String surname, String patronymic, String numberBicycle, String numberPhone, int id, String date) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.numberBicycle = numberBicycle;
        this.numberPhone = numberPhone;
        this.id = id;
        this.dateRegistration = date;
    }
}
