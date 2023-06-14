package com.noximity.storage;

import com.noximity.NoxiBot;
import com.noximity.console.Console;
import com.noximity.console.LogType;
import com.noximity.users.Profile;

import java.sql.*;


public class Database {
    private Connection connection;

    public void connect() {
        try {
            Console.log(LogType.INFO, "Connecting to the database...");
            Class.forName("com.mysql.cj.jdbc.Driver");

            String host = NoxiBot.getBot().getConfig().getString("database.host");
            String port = NoxiBot.getBot().getConfig().getString("database.port");
            String databaseName = NoxiBot.getBot().getConfig().getString("database.name");
            String username = NoxiBot.getBot().getConfig().getString("database.username");
            String password = NoxiBot.getBot().getConfig().getString("database.password");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?useSSL=false&serverTimezone=UTC";

            connection = DriverManager.getConnection(url, username, password);

            Console.log(LogType.INFO, "Successfully connected to the database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createWarningsTable() {
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS warnings ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "user_id VARCHAR(50),"
                    + "warning_id INT,"
                    + "reason VARCHAR(255),"
                    + "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ")";

            statement.executeUpdate(sql);
            Console.log(LogType.INFO, "Warnings table created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Profile getProfile(String userId) {
        String sql = "SELECT * FROM warnings WHERE user_id = ?";
        Profile profile = new Profile(userId);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    profile.setWarningsCount(resultSet.getInt("warning_id"));
                }
            }
            return profile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profile;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                Console.log(LogType.INFO, "Connection closed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void printWarningsTable() {
        try {
            // Create the SQL statement
            String sql = "SELECT * FROM warnings";
            Statement statement = connection.createStatement();

            // Execute the SQL statement
            ResultSet resultSet = statement.executeQuery(sql);

            // Print the table data
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userId = resultSet.getString("user_id");
                int warningId = resultSet.getInt("warning_id");
                String reason = resultSet.getString("reason");
                String date = resultSet.getString("date");

                System.out.println("ID: " + id);
                System.out.println("User ID: " + userId);
                System.out.println("Warning ID: " + warningId);
                System.out.println("Reason: " + reason);
                System.out.println("Date: " + date);
                System.out.println("-------------------------");
            }

            // Close resources
            resultSet.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
