package com.noximity.listeners;

import com.noximity.bot.Bot;
import com.noximity.tools.TimeoutReason;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class TimeoutCommandListener extends ListenerAdapter {

    private final Bot bot;

    public TimeoutCommandListener(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equalsIgnoreCase("timeout")) {

            Member member = event.getMember();

            if (!bot.getPermissions().canTimeout(member) && !member.hasPermission(Permission.ADMINISTRATOR)) {
                event.replyEmbeds(new EmbedBuilder().setTitle("Noximity Punishments").setDescription("You do not have permission to use this command! (" + event.getUser().getAsMention() + ")").setColor(Color.RED).build()).queue(
                        message -> message.deleteOriginal().queueAfter(5, TimeUnit.SECONDS)
                );
                return;
            }

            User user = event.getOption("user").getAsUser();
            User issuer = event.getUser();
            String timeUnit = event.getOption("time-unit").getAsString();
            int time = event.getOption("time").getAsInt();
            String reason = event.getOption("reason").getAsString();
            String description = TimeoutReason.getDescription(reason);


            AtomicBoolean success = new AtomicBoolean(false);

            event.getGuild().findMembers(memberToKick -> {

                if (success.get()) return false;

                if (!memberToKick.getId().equals(user.getId())) {
                    return false;
                }

                if (memberToKick.hasPermission(Permission.ADMINISTRATOR)) {
                    event.replyEmbeds(new EmbedBuilder().setTitle("Noximity Punishments").setDescription("You cannot timeout an administrator! (" + event.getUser().getAsMention() + ")").setColor(Color.RED).build()).queue(
                            message -> message.deleteOriginal().queueAfter(5, TimeUnit.SECONDS)
                    );
                    success.set(true);
                    return true;
                }


                event.replyEmbeds(getResponse(user, issuer, timeUnit, time, reason, description).build()).queue(message -> {
                    event.getGuild().timeoutFor(user, time, getTimeUnit(timeUnit)).queue();
                });
                success.set(true);
                return true;
            });
        }
    }

    private TimeUnit getTimeUnit(String timeUnit) {
        return switch (timeUnit) {
            case "seconds" -> TimeUnit.SECONDS;
            case "minutes" -> TimeUnit.MINUTES;
            case "hours" -> TimeUnit.HOURS;
            case "days" -> TimeUnit.DAYS;
            default -> TimeUnit.SECONDS;
        };
    }

    public EmbedBuilder getResponse(User user, User issuer, String timeUnit, int time, String reason, String description) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Noximity Punishments");
        embed.setDescription("The user " + user.getAsMention() + " has been timed-out by " + issuer.getAsMention());
        embed.addField(" ", " ", false);
        embed.addField("**Reason:**", reason, false);
        embed.addField("**Description**", description, false);
        embed.addField("**Time:**", time + " " + timeUnit, false);
        embed.addField("**Username:**", user.getAsTag(), false);
        embed.setThumbnail("https://cdn.discordapp.com/attachments/980355762616418325/1105190251573555220/pngwing.com.png");
        embed.setColor(Color.GRAY);
        return embed;
    }

}
