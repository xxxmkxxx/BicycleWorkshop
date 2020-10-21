package com.xxxmkxxx.BicycleWorkshop;

import java.util.List;

public class BicycleWorkshop{
    public static void main(String []args) {
        IWorkingVithData objectSaveData = (IWorkingVithData) SafeData.chooseVariantsSafeData();

        Order order = new Order(objectSaveData);
        List <Order> listOrders = order.readOrders();


    }
}
