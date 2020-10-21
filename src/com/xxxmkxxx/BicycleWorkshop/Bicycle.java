package com.xxxmkxxx.BicycleWorkshop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Bicycle {
    private String numberBicycle;
    private int state = 50;
    private String breakage[];

    private static int algorythmComputationState(){
        return 0;
    }

//Геттеры для получения полей
    public int getState() {
        return state;
    }

    public String[] getBreakage() {
        return breakage;
    }

    public Bicycle(Owner owner, String ... breakage){
        algorythmComputationState();
        numberBicycle = owner.getNumberBicycle();
        this.breakage = breakage;
    }

    public Bicycle(int state, Owner owner, String ... breakage){
        numberBicycle = owner.getNumberBicycle();
        this.breakage = breakage;
        this.state = state;
    }
}
