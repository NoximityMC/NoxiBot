package com.noximity.console;

public enum LogType {
    INFO(TextColor.ORANGE + "NoxiBot" + TextColor.RESET + " | " + TextColor.GREEN + "INFO"),
    WARNING(TextColor.ORANGE + "NoxiBot" + TextColor.RESET + " | " + TextColor.YELLOW + "WARNING"),
    ERROR(TextColor.ORANGE + "NoxiBot" + TextColor.RESET + " | " + TextColor.RED + "ERROR");

    private String prefix;

    LogType(String prefix){
        this.prefix = prefix;
    }

    public String getPrefix(){
        return prefix;
    }
}
