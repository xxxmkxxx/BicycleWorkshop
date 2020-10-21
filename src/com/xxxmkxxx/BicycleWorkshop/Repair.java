package com.xxxmkxxx.BicycleWorkshop;

import java.util.Date;
import java.util.Objects;

public class Repair{
    private int cost = 500;
    private String dateToFinalRepair;
    private String numberBicycle;

// Алгоритм вычисления стоимости ремонта
    private static int algorythmComputationCost(){
        return 0;
    }

//Алгоритм вычисления даты окончания ремонта
    private static String algorythmComputationDateToFinalRepair(){
        return null;
    }

//Геттеры для получения полей
    public int getCost() {
        return cost;
    }

    public String getDateToFinalRepair() {
        return dateToFinalRepair;
    }

    public Repair(Owner owner, Bicycle bicycle){
        numberBicycle = owner.getNumberBicycle();
        algorythmComputationCost();
        algorythmComputationDateToFinalRepair();
    }

    public Repair(int cost, Owner owner, Bicycle bicycle){
        numberBicycle = owner.getNumberBicycle();
        this.cost = cost;
    }
}
