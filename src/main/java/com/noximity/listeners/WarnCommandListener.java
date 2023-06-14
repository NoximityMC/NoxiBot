package com.noximity.listeners;

import com.noximity.NoxiBot;
import com.noximity.bot.Bot;
import com.noximity.users.Profile;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class WarnCommandListener extends ListenerAdapter {

    private final Bot bot;

    public WarnCommandListener(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("warn")) {

            Member member = event.getMember();

            if (!bot.getPermissions().canWarn(member) && !member.hasPermission(Permission.ADMINISTRATOR)) {
                event.replyEmbeds(new EmbedBuilder().setTitle("Noximity Punishments").setDescription("You do not have permission to use this command! (" + event.getUser().getAsMention() + ")" ).setColor(Color.RED).build()).queue(
                        message -> message.deleteOriginal().queueAfter(5, TimeUnit.SECONDS)
                );
                return;
            }

            User user = Objects.requireNonNull(event.getOption("user")).getAsUser();
            User issuer = event.getUser();
            String reason = Objects.requireNonNull(event.getOption("reason")).getAsString();

            Profile profile = NoxiBot.getDatabase().getProfile(user.getId());
            profile.newWarning(profile.getWarningsCount() + 1, reason);

            event.replyEmbeds(getResponse(user, issuer, reason, profile.getWarningsCount()).build()).queue();
        }
    }


    public EmbedBuilder getResponse(User user, User issuer, String reason, int warningCount) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Noximity Punishments");
        embed.setDescription("The user " + user.getAsMention() + " has been warned by " + issuer.getAsMention());
        embed.addField("**Reason:**", reason, false);
        embed.addField("**Username:**", user.getAsTag(), false);
        embed.addField("**Warnings Count:**", warningCount + "", false);
        embed.setThumbnail("https://www.pngall.com/wp-content/uploads/8/Warning-PNG-Free-Image.png");
        embed.setColor(Color.YELLOW);
        return embed;
    }


}
