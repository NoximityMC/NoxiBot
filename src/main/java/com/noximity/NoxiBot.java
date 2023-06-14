package com.noximity;

import com.noximity.bot.Bot;
import com.noximity.console.ConsoleCommandService;
import com.noximity.storage.Database;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoxiBot {

    private static final Bot bot = new Bot();
    private static ConsoleCommandService consoleCommandService;
    private static final Database database = new Database();

    public static void main(String[] args) {

        bot.init();
        startConsoleCommandService();
        database.connect();
        database.createWarningsTable();

    }

    private static void startConsoleCommandService() {

        consoleCommandService = new ConsoleCommandService();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(consoleCommandService);

    }

    public static Bot getBot() {
        return bot;
    }

    public static ConsoleCommandService getConsoleCommandService() {
        return consoleCommandService;
    }

    public static Database getDatabase() {
        return database;
    }

}