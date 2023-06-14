package com.noximity.users;

import com.noximity.NoxiBot;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Profile {


    private final String userID;

    private int warnings = 0;

    public Profile(String userID) {

        this.userID = userID;

    }

    // GETTERS

    public String getUserID() {
        return userID;
    }

    public int getWarningsCount() {
        return warnings;
    }

    // SETTERS

    public void setWarningsCount(int warnings) {
        this.warnings = warnings;
    }

    public void addWarning() {
        this.warnings++;
    }

    public void removeWarning() {
        this.warnings--;
    }

    // OTHERS

    public void resetWarnings() {
        this.warnings = 0;
    }

    public void newWarning(int warningID, String reason) {
        this.warnings++;

        try {
            Connection connection = NoxiBot.getDatabase().getConnection();
            String sql = "INSERT INTO warnings (user_id, warning_id, reason) VALUES (?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, this.userID);
                statement.setInt(2, warningID);
                statement.setString(3, reason);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
