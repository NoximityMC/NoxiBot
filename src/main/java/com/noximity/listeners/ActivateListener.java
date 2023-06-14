package com.noximity.listeners;

import com.noximity.bot.Bot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ActivateListener extends ListenerAdapter {

    private final Bot bot;

    public ActivateListener(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        Guild guild = event.getGuild();

        bot.setServer(guild);
        bot.registerCommands();

    }
}
