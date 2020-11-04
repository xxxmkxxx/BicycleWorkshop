package com.xxxmkxxx.BicycleWorkshop.gui.tableviews;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Repairs {
    private StringProperty cost = new SimpleStringProperty();
    private StringProperty term = new SimpleStringProperty();
    private StringProperty numberBicycle = new SimpleStringProperty();

    public String getCost() {
        return cost.get();
    }

    public StringProperty costProperty() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost.set(cost);
    }

    public String getTerm() {
        return term.get();
    }

    public StringProperty termProperty() {
        return term;
    }

    public void setTerm(String term) {
        this.term.set(term);
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

    public Repairs(String cost, String term, String numberBicycle) {
        setCost(cost);
        setTerm(term);
        setNumberBicycle(numberBicycle);
    }
}
