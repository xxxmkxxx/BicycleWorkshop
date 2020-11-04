package com.xxxmkxxx.BicycleWorkshop.gui.tableviews;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Owners {
    private StringProperty name = new SimpleStringProperty();
    private StringProperty surName = new SimpleStringProperty();
    private StringProperty patronimyc = new SimpleStringProperty();
    private StringProperty numberPhone = new SimpleStringProperty();
    private StringProperty id = new SimpleStringProperty();

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurName() {
        return surName.get();
    }

    public StringProperty surNameProperty() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName.set(surName);
    }

    public String getPatronimyc() {
        return patronimyc.get();
    }

    public StringProperty patronimycProperty() {
        return patronimyc;
    }

    public void setPatronimyc(String patronimyc) {
        this.patronimyc.set(patronimyc);
    }

    public String getNumberPhone() {
        return numberPhone.get();
    }

    public StringProperty numberPhoneProperty() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone.set(numberPhone);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public Owners(String id, String surName, String name, String patronimyc, String numberPhone) {
        setId(id);
        setSurName(surName);
        setName(name);
        setPatronimyc(patronimyc);
        setNumberPhone(numberPhone);
    }
}
