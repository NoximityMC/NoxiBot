package com.noximity.bot;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.util.Optional;

public enum Channel {

    COMMANDS(1104812707955347537L),
    TICKETS(0L),
    APPLICATIONS(0L),
    LOGS(0L);

    private final long channelID;

    Channel(long channelID) {
        this.channelID = channelID;
    }

    public long getChannelID() {
        return channelID;
    }

    /**
     * The asTextChannel function is used to convert a channel ID into a TextChannel object.
     *
     * @param bot Get the jda object from the bot class
     * @return An Optional<TextChannel> object
     */
    public Optional<TextChannel> asTextChannel(Bot bot) {
        try {
            return Optional.ofNullable(bot.getJda().getTextChannelById(channelID));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * The requestTextChannel function is a helper function that returns an Optional<TextChannel> object.
     * The purpose of this function is to return the TextChannel object associated with the given channelID,
     * if it exists. If no such TextChannel exists, then an empty Optional<TextChannel> will be returned instead.
     *
     * @param bot       Get the jda instance from the bot
     * @param channelID Get the text channel by its id
     * @return An optional<textchannel>; object
     */
    public static Optional<TextChannel> requestTextChannel(Bot bot, long channelID) {
        try {
            return Optional.ofNullable(bot.getJda().getTextChannelById(channelID));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * The requestTextChannel function is a helper function that returns an Optional<TextChannel> object.
     * The purpose of this function is to return the TextChannel object associated with the given channelID,
     * if it exists. If no such TextChannel exists, then an empty Optional<TextChannel> will be returned instead.
     *
     * @param bot       Get the jda instance from the bot class
     * @param channelID Get the text channel by its id
     * @return A textchannel
     */
    public static Optional<TextChannel> requestTextChannel(Bot bot, String channelID) {
        try {
            return Optional.ofNullable(bot.getJda().getTextChannelById(channelID));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

