package com.noximity.commands;

import com.noximity.tools.TimeoutReason;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class BanCommand {

    private final CommandData command;

    public BanCommand() {

        OptionData reasonData = new OptionData(OptionType.STRING, "reason", "The reason for the timeout", true);

        for (TimeoutReason reason : TimeoutReason.values()) {
            reasonData.addChoice(reason.getReason(), reason.getReason());
        }

        this.command = Commands.slash("ban", "Ban a user from the server")
                .addOption(OptionType.USER, "user", "The user to ban", true)
                .addOptions(reasonData);
    }

    public CommandData getCommand() {
        return command;
    }
}
