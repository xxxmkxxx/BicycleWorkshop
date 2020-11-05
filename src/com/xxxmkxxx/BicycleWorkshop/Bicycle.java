package com.xxxmkxxx.BicycleWorkshop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Bicycle {
    private String numberBicycle;
    private int state = 50;
    private String breakage[] = new String[1];

    private int algorythmComputationState(){
        int tempState = (13 - breakage.length) * 10;

        if(breakage.length >= 10) {
            tempState = 90;
        }

        return tempState;
    }

//Геттеры для получения полей
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setBreakage(String[] breakage) {
        this.breakage = breakage;
    }

    public String[] getBreakage() {
        return breakage;
    }

    public Bicycle(Owner owner, String ... breakage){
        state = algorythmComputationState();
        numberBicycle = owner.getNumberBicycle();
        this.breakage = breakage;
    }

    public Bicycle(int state, Owner owner, String ... breakage){
        numberBicycle = owner.getNumberBicycle();
        this.breakage = breakage;
        this.state = state;
    }
}
