package com.noximity.console;

import com.noximity.NoxiBot;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleCommandService implements Runnable {

    private final List<String> commands = Arrays.asList("stop", "help", "warnings");

    @Override
    public void run() {
        System.out.println("Type \"help\" for a list of commands.");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (commands.contains(input)) {
                switch (input) {
                    case "stop" -> System.exit(0);
                    case "help" -> Console.log(LogType.INFO, "Available commands: " + commands);
                    case "warnings" -> NoxiBot.getDatabase().printWarningsTable();
                }
            } else {
                Console.log(LogType.ERROR, "Invalid command. Type \"help\" for a list of commands.");
            }
        }

    }
}
