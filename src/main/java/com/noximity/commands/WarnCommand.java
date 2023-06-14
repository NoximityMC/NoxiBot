package com.noximity.commands;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class WarnCommand {

    private final CommandData command;

    public WarnCommand() {
        this.command = Commands.slash("warn", "Warn a user")
                .addOption(OptionType.USER, "user", "The user to warn", true)
                .addOption(OptionType.STRING, "reason", "The reason for the warning", true);
    }

    public CommandData getCommand() {
        return command;
    }

}
