package com.xxxmkxxx.BicycleWorkshop.Logs;

public class Log {
    private int id;
    private String name;
    private String logInfo;
    private String typeLog;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public String getTypeLog() {
        return typeLog;
    }

    public Log(int id, String name, String text, String type) {
        this.id = id;
        this.name = name;
        logInfo = text;
        typeLog = type;
    }

    public Log() {}
}
