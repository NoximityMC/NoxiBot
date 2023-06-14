package com.noximity.console;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {

    public static void log(LogType type, String message) {
        System.out.println("[" + getDTFormatted() + "] " + type.getPrefix() + TextColor.RESET + " | " + TextColor.WHITE + message + TextColor.RESET);
    }

    private static String getDTFormatted() {
        // Gets the date and time formatted as "MM/DD/YYYY HH:MM:SS"
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }

}
