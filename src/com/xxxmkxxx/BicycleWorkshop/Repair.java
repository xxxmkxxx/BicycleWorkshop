package com.xxxmkxxx.BicycleWorkshop;

import java.util.Date;
import java.util.Objects;

public class Repair{
    private int cost = 500;
    private String dateToFinalRepair;
    private String numberBicycle;

// Алгоритм вычисления стоимости ремонта
    private static int algorythmComputationCost(Bicycle bicycle){
        return (100 - bicycle.getState()) * 100 + 200 ;
    }

//Алгоритм вычисления даты окончания ремонта
    private static String algorythmComputationDateToFinalRepair(Owner owner){
        return owner.getDateRegistration();
    }

//Геттеры для получения полей
    public int getCost() {
        return cost;
    }

    public String getDateToFinalRepair() {
        return dateToFinalRepair;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Repair(Owner owner, Bicycle bicycle){
        numberBicycle = owner.getNumberBicycle();
        cost = algorythmComputationCost(bicycle);
        algorythmComputationDateToFinalRepair(owner);
    }

    public Repair(int cost, Owner owner, Bicycle bicycle){
        numberBicycle = owner.getNumberBicycle();
        this.cost = cost;
    }
}
