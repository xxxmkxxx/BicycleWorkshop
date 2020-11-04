package com.xxxmkxxx.BicycleWorkshop.gui.tableviews;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bicycles {
    private StringProperty numberBicycle = new SimpleStringProperty();
    private StringProperty numberOwner = new SimpleStringProperty();
    private StringProperty state = new SimpleStringProperty();
    private StringProperty breakages = new SimpleStringProperty();

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

    public String getState() {
        return state.get();
    }

    public StringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public String getBreakages() {
        return breakages.get();
    }

    public StringProperty breakagesProperty() {
        return breakages;
    }

    public void setBreakages(String breakages) {
        this.breakages.set(breakages);
    }

    public Bicycles(String numberBicycle, String numberOwner, String state, String breakages) {
        setNumberBicycle(numberBicycle);
        setNumberOwner(numberOwner);
        setState(state);
        setBreakages(breakages);
    }
}
