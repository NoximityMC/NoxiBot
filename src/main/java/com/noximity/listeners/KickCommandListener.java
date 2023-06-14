package com.noximity.listeners;

import com.noximity.bot.Bot;
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

public class KickCommandListener extends ListenerAdapter {

    private final Bot bot;

    public KickCommandListener(Bot bot) {
        this.bot = bot;

    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("kick")) {

            Member member = event.getMember();

            if (!bot.getPermissions().canKick(member) && !member.hasPermission(Permission.ADMINISTRATOR)) {
                event.replyEmbeds(new EmbedBuilder().setTitle("Noximity Punishments").setDescription("You do not have permission to use this command! (" + event.getUser().getAsMention() + ")").setColor(Color.RED).build()).queue(
                        message -> message.deleteOriginal().queueAfter(5, TimeUnit.SECONDS)
                );
                return;
            }

            User user = Objects.requireNonNull(event.getOption("user")).getAsUser();
            User issuer = event.getUser();
            String reason = Objects.requireNonNull(event.getOption("reason")).getAsString();

            AtomicBoolean success = new AtomicBoolean(false);

            event.getGuild().findMembers(memberToKick -> {

                if (success.get()) return false;

                if (!memberToKick.getId().equals(user.getId())) {
                    return false;
                }

                if (memberToKick.hasPermission(Permission.ADMINISTRATOR)) {
                    event.replyEmbeds(new EmbedBuilder().setTitle("Noximity Punishments").setDescription("You cannot kick an administrator! (" + event.getUser().getAsMention() + ")").setColor(Color.RED).build()).queue(
                            message -> message.deleteOriginal().queueAfter(5, TimeUnit.SECONDS)
                    );
                    success.set(true);
                    return true;
                }


                event.replyEmbeds(getResponse(user, issuer, reason).build()).queue(message -> memberToKick.kick().queue());
                success.set(true);
                return true;
            });
        }
    }


    public EmbedBuilder getResponse(User user, User issuer, String reason) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Noximity Punishments");
        embed.setDescription("The user " + user.getAsMention() + " has been kicked by " + issuer.getAsMention());
        embed.addField("**Reason:**", reason, false);
        embed.addField("**Username:**", user.getAsTag(), false);
        embed.setThumbnail("https://www.clipartmax.com/png/full/288-2880318_door-door-open-icon-png.png");
        embed.setColor(Color.CYAN);
        return embed;
    }

}
