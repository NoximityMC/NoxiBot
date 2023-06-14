package com.noximity.commands;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class KickCommand {

    private final CommandData command;

    public KickCommand() {
        this.command = Commands.slash("kick", "Kick a user from the server")
                .addOption(OptionType.USER, "user", "The user to kick", true)
                .addOption(OptionType.STRING, "reason", "The reason for the warning", true);
    }

    public CommandData getCommand() {
        return command;
    }

}
