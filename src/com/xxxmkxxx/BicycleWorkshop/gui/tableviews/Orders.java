package com.xxxmkxxx.BicycleWorkshop.gui.tableviews;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Orders {
    private StringProperty id = new SimpleStringProperty();
    private StringProperty date = new SimpleStringProperty();
    private StringProperty numberBicycle = new SimpleStringProperty();
    private StringProperty numberOwner = new SimpleStringProperty();

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getNumberBicycle() {
        return numberBicycle.get();
    }

    public StringProperty numberBicycleProperty() {
        return numberBicycle;
    }

    public void setNumberBicycle(String numberBicycle) {
        this.numberBicycle.set(numberBicycle);
    }

    public String getNumberOwner() {
        return numberOwner.get();
    }

    public StringProperty numberOwnerProperty() {
        return numberOwner;
    }

    public void setNumberOwner(String numberOwner) {
        this.numberOwner.set(numberOwner);
    }

    public Orders(String id, String date, String numberBicycle, String numberOwner) {
        setId(id);
        setDate(date);
        setNumberBicycle(numberBicycle);
        setNumberOwner(numberOwner);
    }
}
