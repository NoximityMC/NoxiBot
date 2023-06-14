package com.noximity.commands;

import com.noximity.tools.TimeoutReason;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class TimeoutCommand {

    private final CommandData command;

    public TimeoutCommand() {

        OptionData reasonData = new OptionData(OptionType.STRING, "reason", "The reason for the timeout", true);

        for(TimeoutReason reason : TimeoutReason.values()) {
            reasonData.addChoice(reason.getReason(), reason.getReason());
        }

        this.command = Commands.slash("timeout", "Timeout a user in the server")
                .addOption(OptionType.USER, "user", "The user to timeout", true)
                .addOptions(new OptionData(OptionType.STRING, "time-unit", "The unit of time to timeout the user for", true)
                        .addChoice("seconds", "seconds")
                        .addChoice("minutes", "minutes")
                        .addChoice("hours", "hours")
                        .addChoice("days", "days"))
                .addOption(OptionType.INTEGER, "time", "The amount of time to timeout the user for", true)
                .addOptions(reasonData);


    }

    public CommandData getCommand() {
        return command;
    }

}
