package com.xxxmkxxx.BicycleWorkshop.Logs;

import com.sun.javafx.collections.MappingChange;

import java.util.HashMap;
import java.util.Map;

public class Logger {
    private Map <String, Log> listLogs = new HashMap();

//Метод для определения логов
    private void setLogs() {
        String nameLog;

        listLogs.put(nameLog = "accaunt not found", new Log(1, nameLog, "Sorry, this accaunt not found!", "sqllite"));
        listLogs.put(nameLog = "wrong password", new Log(2, nameLog, "Sorry, you inputed wrong password!", "sqllite"));
        listLogs.put(nameLog = "login successful", new Log(3, nameLog, "You login successful!", "sqllite"));
    }

//Метод для получения лога по имени
    public Log getLogByName(String nameLog) {
        return listLogs.get(nameLog);
    }

    public Logger() {
        setLogs();
    }
}
