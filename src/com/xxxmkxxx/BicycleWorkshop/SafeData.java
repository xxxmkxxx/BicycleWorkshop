package com.xxxmkxxx.BicycleWorkshop;

import com.xxxmkxxx.BicycleWorkshop.sql.sqlLite.SQLlite;
import com.xxxmkxxx.BicycleWorkshop.xml.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SafeData {
    private static Map variantsSafeData = new HashMap <String, Object>();

    private static void setVariantsSafeData(){
        variantsSafeData.put("xml", new XML("list.xml"));
        variantsSafeData.put("db", new SQLlite("list.db"));
    }

    public static Object chooseVariantsSafeData(){
        setVariantsSafeData();
        String nameFile;

        int cycles = 0;
        while(cycles <= 5){
            System.out.print("Введите название файла включая расширение... ");
            nameFile = new Scanner(System.in).nextLine();

            if(variantsSafeData.containsKey(nameFile.split("\\.")[nameFile.split("\\.").length - 1])){
                System.out.println("Вы выбрали работу с " +
                        nameFile.split("\\.")[nameFile.split("\\.").length - 1] +
                        " расширением файла!");
                return variantsSafeData.get(nameFile.split("\\.")[nameFile.split("\\.").length - 1]);
            }
            else{
                System.out.println("Тип введённого вами файла не соответствует типу, с которым может работать программа! Попробуйте ввести другое название!");
                cycles++;
            }

            if(cycles == 5){
                System.out.println("Вы использовали 5 попыток ввода корректного имени файла! Попробуйте в следущий раз!");
                System.exit(0);
            }
        }

        return null;
    }



}
